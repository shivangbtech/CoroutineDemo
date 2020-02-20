package com.coroutinedemo.network

object NetworkConfig {
    // Error Codes
    const val RESPONSE_CODE_NETWORK = 98                      // Network
    const val RESPONSE_CODE_UNEXPECTED = 99                   // Unexpected or Unknown
    const val RESPONSE_CODE_SUCCESS = 200                     // Success
    const val RESPONSE_CODE_NO_RESPONSE = 444                 // No Response
    const val RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500       // Internal Server Error
    const val RESPONSE_CODE_UNAUTHORIZED = 401                // Unauthorized
    const val RESPONSE_CODE_FORBIDDEN = 403                   // Forbidden
    const val RESPONSE_CODE_NOT_FOUND = 404                   // Not Found
    const val RESPONSE_CODE_UN_PROCESSABLE_ENTITY = 422       // Un-processable Entity
    const val RESPONSE_CODE_BAD_REQUEST = 400                 // Bad Request
    const val RESPONSE_CODE_CONFLICT = 409                    // Conflict
    const val RESPONSE_CODE_SERVICE_UNAVAILABLE = 503         // Service Unavailable
    const val RESPONSE_CODE_GATEWAY_TIMEOUT = 504             // Gateway Timeout

    // Error Messages
    const val ERROR_MESSAGE_UNEXPECTED = "Unexpected Error occurred!"
    const val ERROR_MESSAGE_SERVER = "Server Error Occurred!"
    const val ERROR_MESSAGE_NETWORK = "Please check your internet connection."
    const val ERROR_MESSAGE_FORBIDDEN = "Access Denied!"
    const val ERROR_MESSAGE_SERVICE_UNAVAILABLE = "SERVICE UNAVAILABLE!"
    const val ERROR_MESSAGE_INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR!"
    const val ERROR_MESSAGE_NOT_FOUND = "No Response Found!"
    const val ERROR_MESSAGE_UNAUTHORIZED = "Authentication Failed! Please Login Again!"
    const val ERROR_MESSAGE_BAD_REQUEST = "Unable to process the request!"
    const val ERROR_MESSAGE_LARGE_FILE = "File is too large!"
    const val ERROR_MESSAGE_SERVER_NOT_RESPONDING = "Server not responding!"
    const val ERROR_MESSAGE_GATEWAY_TIMEOUT = "GATEWAY TIMEOUT!"

}
