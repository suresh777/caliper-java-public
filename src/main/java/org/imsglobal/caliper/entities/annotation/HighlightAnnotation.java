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
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.BaseEntity;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HighlightAnnotation extends BaseEntity implements Annotation {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("annotated")
    private DigitalResource annotated;

    @JsonProperty("selection")
    private TextPositionSelector selection;

    @JsonProperty("selectionText")
    private String selectionText;

    /**
     * @param builder apply builder object properties to the HighlightAnnotation object.
     */
    protected HighlightAnnotation(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, AnnotationType.HIGHLIGHT_ANNOTATION);
        EntityValidator.checkId("annotated Id", builder.annotated.getId());

        this.type = builder.type;
        this.annotated = builder.annotated;
        this.selection = builder.selection;
        this.selectionText = builder.selectionText;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
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
     * @return the selection
     */
    @Nullable
    public TextPositionSelector getSelection() {
        return selection;
    }

    /**
     * @return the selectionText
     */
    @Nullable
    public String getSelectionText() {
        return selectionText;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEntity.Builder<T>  {
        private String type;
        private DigitalResource annotated;
        private TextPositionSelector selection;
        private String selectionText;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(AnnotationType.HIGHLIGHT_ANNOTATION.getValue());
            selection = new TextPositionSelector();
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
         * @param start
         * @return text position selector.
         */
        public T selectionStart(String start) {
            this.selection.setStart(start);
            return self();
        }

        /**
         * @param end
         * @return text position selector.
         */
        public T selectionEnd(String end) {
            this.selection.setEnd(end);
            return self();
        }

        /**
         * TODO does the builder need to instantiate a new TextPositionSelector
         * per the original constructor or let the user do it?
         * @param selectionText
         * @return annotation selection text.
         */
        public T selectionText(String selectionText) {
            this.selectionText = selectionText;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of HighlightAnnotation.
         */
        public HighlightAnnotation build() {
            return new HighlightAnnotation(this);
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