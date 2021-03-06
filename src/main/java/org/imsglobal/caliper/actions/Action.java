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

package org.imsglobal.caliper.actions;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Action {
    ABANDONED("http://purl.imsglobal.org/vocab/caliper/v1/action#Abandoned"),
    ACTIVATED("http://purl.imsglobal.org/vocab/caliper/v1/action#Activated"),
    ATTACHED("http://purl.imsglobal.org/vocab/caliper/v1/action#Attached"),
    BOOKMARKED("http://purl.imsglobal.org/vocab/caliper/v1/action#Bookmarked"),
    CHANGED_RESOLUTION("http://purl.imsglobal.org/vocab/caliper/v1/action#ChangedResolution"),
    CHANGED_SIZE("http://purl.imsglobal.org/vocab/caliper/v1/action#ChangedSize"),
    CHANGED_SPEED("http://purl.imsglobal.org/vocab/caliper/v1/action#ChangedSpeed"),
    CHANGED_VOLUME("http://purl.imsglobal.org/vocab/caliper/v1/action#ChangedVolume"),
    CLASSIFIED("http://purl.imsglobal.org/vocab/caliper/v1/action#Classified"),
    CLOSED_POPOUT("http://purl.imsglobal.org/vocab/caliper/v1/action#ClosedPopout"),
    COMMENTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Commented"),
    COMPLETED("http://purl.imsglobal.org/vocab/caliper/v1/action#Completed"),
    DEACTIVATED("http://purl.imsglobal.org/vocab/caliper/v1/action#Deactivated"),
    DESCRIBED("http://purl.imsglobal.org/vocab/caliper/v1/action#Described"),
    DISLIKED("http://purl.imsglobal.org/vocab/caliper/v1/action#Disliked"),
    DISABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/vocab/caliper/v1/action#DisabledClosedCaptioning"),
    ENABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/vocab/caliper/v1/action#EnabledClosedCaptioning"),
    ENDED("http://purl.imsglobal.org/vocab/caliper/v1/action#Ended"),
    ENTERED_FULLSCREEN("http://purl.imsglobal.org/vocab/caliper/v1/action#EnteredFullscreen"),
    EXITED_FULLSCREEN("http://purl.imsglobal.org/vocab/caliper/v1/action#ExitedFullscreen"),
    FORWARDED_TO("http://purl.imsglobal.org/vocab/caliper/v1/action#ForwardedTo"),
    GRADED("http://purl.imsglobal.org/vocab/caliper/v1/action#Graded"),
    HID("http://purl.imsglobal.org/vocab/caliper/v1/action#Hid"),
    HIGHLIGHTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Highlighted"),
    JUMPED_TO("http://purl.imsglobal.org/vocab/caliper/v1/action#JumpedTo"),
    IDENTIFIED("http://purl.imsglobal.org/vocab/caliper/v1/action#Identified"),
    LIKED("http://purl.imsglobal.org/vocab/caliper/v1/action#Liked"),
    LINKED("http://purl.imsglobal.org/vocab/caliper/v1/action#Linked"),
    LOGGED_IN("http://purl.imsglobal.org/vocab/caliper/v1/action#LoggedIn"),
    LOGGED_OUT("http://purl.imsglobal.org/vocab/caliper/v1/action#LoggedOut"),
    MUTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Muted"),
    NAVIGATED_TO("http://purl.imsglobal.org/vocab/caliper/v1/action#NavigatedTo"),
    OPENED_POPOUT("http://purl.imsglobal.org/vocab/caliper/v1/action#OpenedPopout"),
    PAUSED("http://purl.imsglobal.org/vocab/caliper/v1/action#Paused"),
    RANKED("http://purl.imsglobal.org/vocab/caliper/v1/action#Ranked"),
    QUESTIONED("http://purl.imsglobal.org/vocab/caliper/v1/action#Questioned"),
    RECOMMENDED("http://purl.imsglobal.org/vocab/caliper/v1/action#Recommended"),
    REPLIED("http://purl.imsglobal.org/vocab/caliper/v1/action#Replied"),
    RESTARTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Restarted"),
    RESUMED("http://purl.imsglobal.org/vocab/caliper/v1/action#Resumed"),
    REVIEWED("http://purl.imsglobal.org/vocab/caliper/v1/action#Reviewed"),
    REWOUND("http://purl.imsglobal.org/vocab/caliper/v1/action#Rewound"),
    SEARCHED("http://purl.imsglobal.org/vocab/caliper/v1/action#Searched"),
    SHARED("http://purl.imsglobal.org/vocab/caliper/v1/action#Shared"),
    SHOWED("http://purl.imsglobal.org/vocab/caliper/v1/action#Showed"),
    SKIPPED("http://purl.imsglobal.org/vocab/caliper/v1/action#Skipped"),
    STARTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Started"),
    SUBMITTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Submitted"),
    SUBSCRIBED("http://purl.imsglobal.org/vocab/caliper/v1/action#Subscribed"),
    TAGGED("http://purl.imsglobal.org/vocab/caliper/v1/action#Tagged"),
    TIMED_OUT("http://purl.imsglobal.org/vocab/caliper/v1/action#TimedOut"),
    VIEWED("http://purl.imsglobal.org/vocab/caliper/v1/action#Viewed"),
    UNMUTED("http://purl.imsglobal.org/vocab/caliper/v1/action#Unmuted");

    private String value;

    private Action(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}