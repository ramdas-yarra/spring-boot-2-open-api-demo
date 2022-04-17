package edu.ram.learning.openapiuidemo.controller;

import edu.ram.learning.openapiuidemo.dto.UserProfile;
import edu.ram.learning.openapiuidemo.exception.Errors;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(info = @Info(description = "API description follows here", title = "Open API v3 demo", version = "v1" ))
@Tag(name = "Users", description = "Sample application to work on open API 3 integration with Spring boot 2")
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface Profile {

    @Operation(description = "Retrieves all users  profile", tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Application completed the request successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserProfile.class)))),
            @ApiResponse(responseCode = "401", description = "Please call the operation with valid authentication bear token", content = @Content),
            @ApiResponse(responseCode = "403", description = "Please make sure you call the operation with valid entitlement authorization token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server failed to process the request", content = @Content),
            @ApiResponse(responseCode = "501", description = "Operation yet to implement", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service unavailable. Please try later", content = @Content)
    })
    @GetMapping
    List<UserProfile> retrieveProfiles(@Parameter(description = "OAuth bear token with valid authentication and authorization entitlements", in = ParameterIn.HEADER, schema = @Schema(type = "string"))
                                            @RequestHeader(value = "authorization", required = true)String authorization,
                                       @Parameter(description = "Trace id to identify a request uniquely.",in = ParameterIn.HEADER, schema = @Schema(type = "string"))
                                            @RequestHeader(value = "trace-id", required = true) String traceId);

    @Operation(description = "Retrieves specific user profile", tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully completed the request", content = @Content(schema =  @Schema(implementation = UserProfile.class) )),
            @ApiResponse(responseCode = "400", description = "Invalid user id passed on the request", content = @Content(schema = @Schema(implementation = Errors.class))),
            @ApiResponse(responseCode = "401", description = "Please call the operation with valid authentication bear token", content = @Content),
            @ApiResponse(responseCode = "403", description = "Please make sure you call the operation with valid entitlement authorization token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Requested user profile doesn't exists on the system", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed process the request", content = @Content),
            @ApiResponse(responseCode = "501", description = "Operation yet to implement", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service unavailable. Please try later", content = @Content)
    })
    @GetMapping(path = "/{userId}")
    UserProfile retrieveUserProfile(@Parameter(description = "OAuth bear token with valid authentication and authorization entitlements", in = ParameterIn.HEADER, schema = @Schema(type = "string"))
                                        @RequestHeader(value = "authorization", required = true) String authorization,
                                    @Parameter(description = "Trace id to identify a request uniquely.",in = ParameterIn.HEADER, required = true, schema = @Schema(type = "string"))
                                        @RequestHeader(value = "trace-id", required = true) String traceId,
                                    @Parameter(description = "profile user identifier", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
                                        @PathVariable("user-id") String userId );

    @Operation(description = "Create user profile", tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User profile created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid details in the input request. please check error details", content = @Content(schema = @Schema(implementation = Errors.class))),
            @ApiResponse(responseCode = "401", description = "User unauthorized to perform the operation"),
            @ApiResponse(responseCode = "403", description = "user doesn't have entitlements to perform the operation"),
            @ApiResponse(responseCode = "500", description = "Application failed to process request"),
            @ApiResponse(responseCode = "501", description = "Operation yet to implement"),
            @ApiResponse(responseCode = "503", description = "Service temporarily unavailable. Please later, if it persists, please reach out to support team")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void create(@Parameter(description = "OAuth bear token with valid authentication and authorization entitlements", in = ParameterIn.HEADER)
                    @RequestHeader(value = "authorization", required = true) String authorization,
                @Parameter(description = "unique trace id to identify a request uniquely.", in = ParameterIn.HEADER, required = true)
                    @RequestHeader(value = "trace-id", required = true) String traceId,
                @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User profile details. No need to populate user id", required = true, content =@Content(schema = @Schema(implementation = UserProfile.class)) )
                    @RequestBody UserProfile userProfile);
}