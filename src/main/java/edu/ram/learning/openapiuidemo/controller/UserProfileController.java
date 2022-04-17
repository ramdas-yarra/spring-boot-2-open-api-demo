package edu.ram.learning.openapiuidemo.controller;

import edu.ram.learning.openapiuidemo.dto.UserProfile;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserProfileController implements Profile{

    @Override
    public List<UserProfile> retrieveProfiles(String authorization, String traceId) {
        return null;
    }

    @Override
    public UserProfile retrieveUserProfile(String authorization, String traceId, String userId) {
        return null;
    }

    @Override
    public void create(String authorization, String traceId, UserProfile userProfile) {

    }
}
