package purr.catshop.base.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Audited
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial", updatable = false, insertable = false)
    var id: Long = 0,
    @CreatedDate
    @Column(
        nullable = false,
        updatable = false,
    )
    @NotAudited
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {
    abstract fun toDTO(): BaseDTO
}
