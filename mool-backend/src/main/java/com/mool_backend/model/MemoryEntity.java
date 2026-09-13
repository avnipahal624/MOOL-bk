package com.mool_backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "memories")
public class MemoryEntity {

    @Id
    private String id;

    private String type;
    private String titleEn;
    private String titleAs;
    private String titleBn;

    @Column(length = 2000)
    private String textEn;
    @Column(length = 2000)
    private String textAs;
    @Column(length = 2000)
    private String textBn;

    @ElementCollection
    @CollectionTable(name = "memory_tags", joinColumns = @JoinColumn(name = "memory_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    private boolean sensitive;
    private long createdAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String v) { this.titleEn = v; }
    public String getTitleAs() { return titleAs; }
    public void setTitleAs(String v) { this.titleAs = v; }
    public String getTitleBn() { return titleBn; }
    public void setTitleBn(String v) { this.titleBn = v; }
    public String getTextEn() { return textEn; }
    public void setTextEn(String v) { this.textEn = v; }
    public String getTextAs() { return textAs; }
    public void setTextAs(String v) { this.textAs = v; }
    public String getTextBn() { return textBn; }
    public void setTextBn(String v) { this.textBn = v; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public boolean isSensitive() { return sensitive; }
    public void setSensitive(boolean sensitive) { this.sensitive = sensitive; }
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
