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

package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.imsglobal.caliper.events.BaseEvent;

import javax.annotation.Nullable;
import java.util.Map;

public class TestFreeFormEvent extends BaseEvent {

    @JsonProperty("extensions")
    private Map<String, Object> extensions;

    @JsonIgnore
    //private static final Logger log = LoggerFactory.getLogger(TestMinimalEvent.class);

    /**
     * Utilize builder to construct Event.  Validate object copy rather than the
     * builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected TestFreeFormEvent(Builder<?> builder) {
        super(builder);

        // EventValidator.checkContext(getContext(), Context.CONTEXT);

        this.extensions = builder.extensions;
    }

    /**
     * Returns a map of additional custom attributes.
     * @return custom extensions (key/value pairs).
     */
    @Nullable
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEvent.Builder<T> {
        private Map<String, Object> extensions = Maps.newHashMap();

        /**
         * Initialize type with default values.
         */
        public Builder() {

        }

        /**
         * @param key
         * @param value
         * @return builder
         */
        public T extension(String key, Object value) {
            this.extensions.put(key, value);
            return self();
        }

        /**
         * @param extensions
         * @return builder
         */
        public T extensions(Map<String, Object> extensions) {
            this.extensions.putAll(extensions);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public TestFreeFormEvent build() {
            return new TestFreeFormEvent(this);
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