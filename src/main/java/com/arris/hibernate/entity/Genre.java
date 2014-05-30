package com.arris.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This genre entity will have details about the program genres.
 * @author Swaroop
 */
@Entity
@Table(name = "Genre")
@SuppressWarnings("javadoc")
public class Genre {

    /**
     * @param id
     * @param name
     * @param description
     * @param activeInd
     */
    public Genre(Long id, String name, String description, Boolean activeInd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.activeInd = activeInd;
    }

    public Genre() {
        super();
    }
    
    @Id
    @SequenceGenerator(name="uuid", sequenceName="uuid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "increment")
    @Column(name = "genre_id", unique=true)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(100)", nullable = true)
    private String description;

    @Column(name = "active_ind", columnDefinition = "boolean default true", nullable = true)
    private Boolean activeInd;

    /**
     * @return the id
     */
    public final Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public final void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the activeInd
     */
    public final Boolean getActiveInd() {
        return activeInd;
    }

    /**
     * @param activeInd the activeInd to set
     */
    public final void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
    }
}
