package purr.catshop.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EntityScan("purr.catshop")
@EnableJpaRepositories("purr.catshop")
@EnableTransactionManagement
class DomainConfig
