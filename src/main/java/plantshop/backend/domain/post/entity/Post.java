package plantshop.backend.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.AuditEntity;
import plantshop.backend.domain.member.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Post extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private Member member;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
}
