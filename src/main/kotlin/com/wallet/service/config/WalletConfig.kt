package com.wallet.service.config

import com.wallet.service.domain.account.dto.AccountConverter
import com.wallet.service.domain.account.dto.AccountFullConverter
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies.STRICT
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
class WalletConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = STRICT
        modelMapper.configuration.isFieldMatchingEnabled = true
        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.addConverter(AccountConverter())
        modelMapper.addConverter(AccountFullConverter())
        return modelMapper
    }
}