/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.entities.annotation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.BaseEntity;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SharedAnnotation extends BaseEntity implements Annotation {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("annotated")
    private DigitalResource annotated;

    @JsonProperty("withAgents")
    private final ImmutableList<Agent> withAgents;

    /**
     * @param builder apply builder object properties to the SharedAnnotation object.
     */
    protected SharedAnnotation(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, AnnotationType.SHARED_ANNOTATION);
        EntityValidator.checkId("annotated Id", builder.annotated.getId());

        this.type = builder.type;
        this.annotated = builder.annotated;
        this.withAgents = ImmutableList.copyOf(builder.withAgents);
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Serialization of DigitalResource associated with this Annotation is limited to
     * the identifying URI only.
     * @return the annotated object's identifier
     */
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "@id")
    @JsonIdentityReference(alwaysAsId = true)
    @Nonnull
    public DigitalResource getAnnotated() {
        return annotated;
    }

    /**
     * Return an immutable view of the withAgents list.
     * @return the users
     */
    @Nullable
    public ImmutableList<Agent> getWithAgents() {
        return withAgents;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEntity.Builder<T>  {
        private String type;
        private DigitalResource annotated;
        private List<Agent> withAgents = Lists.newArrayList();

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(AnnotationType.SHARED_ANNOTATION.getValue());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param annotated
         * @return builder.
         */
        public T annotated(DigitalResource annotated) {
            this.annotated = annotated;
            return self();
        }

        /**
         * @param withAgents
         * @return shared agents.
         */
        public T withAgents(List<Agent> withAgents) {
            this.withAgents = withAgents;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of SharedAnnotation.
         */
        public SharedAnnotation build() {
            return new SharedAnnotation(this);
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