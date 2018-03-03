package com.example.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class RequeryPersistentContextConfig(
        private val dataSource: DataSource
) {
    //@Bean
    //fun connectionProvider()= DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

}