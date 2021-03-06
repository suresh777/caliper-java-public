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

package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.validators.EventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@SupportedActions({
    Action.ATTACHED,
    Action.BOOKMARKED,
    Action.CLASSIFIED,
    Action.COMMENTED,
    Action.DESCRIBED,
    Action.DISLIKED,
    Action.HIGHLIGHTED,
    Action.IDENTIFIED,
    Action.LIKED,
    Action.LINKED,
    Action.RANKED,
    Action.QUESTIONED,
    Action.RECOMMENDED,
    Action.REPLIED,
    Action.SHARED,
    Action.SUBSCRIBED,
    Action.TAGGED
})
public class AnnotationEvent extends BaseEventContext {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(AnnotationEvent.class);

    /**
     * Utilize builder to construct AnnotationEvent.  Validate Annotation object copy rather than the
     * Annotation builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected AnnotationEvent(Builder<?> builder) {
        super(builder);

        EventValidator.checkType(builder.type, EventType.ANNOTATION);
        EventValidator.checkActorType(getActor(), Person.class);
        EventValidator.checkAction(builder.action, AnnotationEvent.class);
        EventValidator.checkObjectType(getObject(), DigitalResource.class);
        EventValidator.checkGeneratedType(getGenerated(), Annotation.class);

        this.type = builder.type;
        this.action = builder.action;
    }

    /**
     * Required.
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * Required.
     * @return the action
     */
    @Override
    @Nonnull
    public String getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEventContext.Builder<T>  {
        private String type;
        private String action;

        /*
         * Constructor
         */
        public Builder() {
            type(EventType.ANNOTATION.getValue());
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
         * @param action
         * @return builder.
         */
        @Override
        public T action(String action) {
            this.action = action;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new AnnotationEvent instance.
         */
        public AnnotationEvent build() {
            return new AnnotationEvent(this);
        }
    }

    /**
     * Self-reference that permits sub-classing of builder.
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