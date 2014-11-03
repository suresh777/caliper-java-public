package org.imsglobal.caliper.profiles;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;

import java.util.List;

public abstract class Profile {

    private LearningContext learningContext;
    private List<String> actions = Lists.newArrayList();
    private List<DigitalResource> fromResources = Lists.newArrayList();
    private List<Object> targets = Lists.newArrayList();
    private List<Object> generateds = Lists.newArrayList();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected Profile(Builder<?> builder) {
        this.learningContext = builder.learningContext;
        this.actions = builder.actions;
        this.fromResources = builder.fromResources;
        this.targets = builder.targets;
        this.generateds = builder.generateds;
    }

    /**
     * @return the learning context.
     */
    public LearningContext getLearningContext() {
        return learningContext;
    }

    /**
     * @return list of actions for a given activity
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * @return navigation history
     */
    public List<DigitalResource> getFromResources() {
        return fromResources;
    }

    /**
     * @return list of target objects for a given activity
     */
    public List<Object> getTargets() {
        return targets;
    }

    /**
     * @return list of generated objects for a given activity
     */
    public List<Object> getGenerateds() {
        return generateds;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).  Given the abstract nature
     * of Profile, the builder's .build() method is omitted.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> {
        private LearningContext learningContext;
        private List<String> actions = Lists.newArrayList();
        private List<DigitalResource> fromResources = Lists.newArrayList();
        private List<Object> targets = Lists.newArrayList();
        private List<Object> generateds = Lists.newArrayList();

        protected abstract T self();

        /**
         * @param learningContext
         * @return builder.
         */
        public T learningContext(LearningContext learningContext) {
            this.learningContext = learningContext;
            return self();
        }

        /**
         * @param actions
         * @return builder.
         */

        public T actions(List<String> actions) {
            this.actions = actions;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */

        public T action(String action) {
            this.actions.add(action);
            return self();
        }

        /**
         * @param fromResources
         * @return builder
         */
        public T fromResources(List<DigitalResource> fromResources) {
            this.fromResources = fromResources;
            return self();
        }

        /**
         * @param fromResource
         * @return builder
         */
        public T fromResource(DigitalResource fromResource) {
            this.fromResources.add(fromResource);
            return self();
        }

        /**
         * @param targets
         * @return builder.
         */
        public T targets(List<Object> targets) {
            this.targets = targets;
            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        public T target(Object target) {
            this.targets.add(target);
            return self();
        }

        /**
         * @param generateds
         * @return builder.
         */
        public T generateds(List<Object> generateds) {
            this.generateds = generateds;
            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        public T generated(Object generated) {
            this.generateds.add(generated);
            return self();
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
}