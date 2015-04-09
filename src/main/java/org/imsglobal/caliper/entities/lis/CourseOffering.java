package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.w3c.Organization;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A CourseOffering is the occurrence of a course in a specific term, semester, etc.  A Caliper
 * CourseOffering provides a subset of the CourseOffering properties specified in the IMS LTI 2.0
 * specification, which in turn, draws inspiration from the IMS LIS 1.0 specification.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "courseNumber",
    "name",
    "description",
    "academicSession",
    "membership",
    "subOrganizationOf",
    "extensions",
    "dateCreated",
    "dateModified"})
public class CourseOffering extends Entity implements Course {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("courseNumber")
    private final String courseNumber;

    @JsonProperty("academicSession")
    private final String academicSession;

    @JsonProperty("membership")
    private final ImmutableList<org.imsglobal.caliper.entities.w3c.Membership> memberships;

    @JsonProperty("subOrganizationOf")
    private final Organization subOrganizationOf;

    /**
     * @param builder apply builder object properties to the CourseSection object.
     */
    protected CourseOffering(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, EntityType.COURSE_OFFERING);

        this.type = builder.type;
        this.courseNumber = builder.courseNumber;
        this.academicSession = builder.academicSession;
        this.memberships = ImmutableList.copyOf(builder.memberships);
        this.subOrganizationOf = builder.subOrganizationOf;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
    }

    /**
     * The course number, such as "Biology 101". In general, this number is not simply a numeric value.
     * @return the course number.
     */
    @Nullable
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * @return academic session
     */
    @Nullable
    public String getAcademicSession() {
        return academicSession;
    }

    /**
     * @return membership
     */
    @Nullable
    public ImmutableList<org.imsglobal.caliper.entities.w3c.Membership> getMembership() {
        return memberships;
    }

    /**
     * @return parent membership
     */
    @Nullable
    public Organization getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private EntityType type;
        private String courseNumber;
        private String academicSession;
        private List<org.imsglobal.caliper.entities.w3c.Membership> memberships = Lists.newArrayList();
        private Organization subOrganizationOf;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.COURSE_OFFERING);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
            return self();
        }

        /**
         * @param courseNumber
         * @return builder.
         */
        public T courseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
            return self();
        }

        /**
         * @param academicSession
         * @return builder.
         */
        public T academicSession(String academicSession) {
            this.academicSession = academicSession;
            return self();
        }

        /**
         * @param memberships
         * @return builder.
         */
        public T memberships(List<org.imsglobal.caliper.entities.w3c.Membership> memberships) {
            this.memberships = memberships;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        public T membership(org.imsglobal.caliper.entities.w3c.Membership membership) {
            this.memberships.add(membership);
            return self();
        }

        /**
         * @param subOrganizationOf
         * @return builder.
         */
        public T subOrganizationOf(Organization subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the CourseOffering.
         */
        public CourseOffering build() {
            return new CourseOffering(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}