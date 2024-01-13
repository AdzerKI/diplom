package ru.kranbe.domain.petition;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.kranbe.domain.BaseEntity;
import ru.kranbe.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "petitions")
@Data
@EqualsAndHashCode(callSuper = false)
public class Petition extends BaseEntity {
    // TODO
    //@Column(name = "submitter_id", nullable = false, updatable = false)
    @OneToOne (fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User submitterId;

    //TODO
    //@Column(name = "operator_id")
    @OneToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User operatorId;

    @Column(name = "type", nullable = false, updatable = false)
    private Type type;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "added_file_list")
    private String addedFileList;

    @Column(name = "operator_decision")
    private String operatorDecision;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "issue_result")
    private IssueResult issueResult;

    @Column(name = "petition_status")
    @Enumerated(EnumType.STRING)
    private PetitionStatus petitionStatus;;
}
