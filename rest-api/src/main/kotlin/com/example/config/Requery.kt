package com.example.config

import com.example.api.tweeter.db.Models
import io.requery.cache.EntityCacheBuilder
import io.requery.sql.KotlinConfiguration
import io.requery.sql.KotlinEntityDataStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.sql.SQLException
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
class RequeryPersistentContextConfig(
        private val dataSource: DataSource
) {
    // https://github.com/requery/requery/blob/master/requery-test/src/test/java/io/requery/test/JPAModelTest.java


    @Throws(SQLException::class)
    @Bean(name = ["KotlinEntityDataStore"])
    fun setup(): KotlinEntityDataStore<Any> {

        val models = Models.DEFAULT
        val cache = EntityCacheBuilder(models)
                .useReferenceCache(false)
                .useSerializableCache(false)
                .build()
        val configuration = KotlinConfiguration(
                dataSource = dataSource,
                model = models,
                statementCacheSize = 0,
                useDefaultLogging = true
                //, cache = cache
        )
        val data = KotlinEntityDataStore<Any>(configuration)

        return data
    }

    //@Bean
    //fun connectionProvider()= DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

}