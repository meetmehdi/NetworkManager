package com.example.raza.networkrequestmanagment.network.constants;

/**
 * Created by Muzammil on 31/01/2017.
 */

public class NetworkResponseCodes
{
    // 1xx informational
    public static int CONTINUE = 100;
    public static int SWITCHING_PROTOCOLS = 101;
    public static int PROCESSING = 102;


    // 2xx success
    public static int OK = 200;
    public static int CREATED = 201;
    public static int ACCEPTED = 202;
    public static int NON_AUTHORITATIVE_INFO = 203;
    public static int NO_CONTENT = 204;
    public static int RESET_CONETNT = 205;
    public static int PARTIAL_CONTENT = 206;
    public static int MULTI_STATUS = 207;
    public static int ALREADY_REPORTED = 208;
    public static int IM_USED = 226;

    // 3xx redirection
    public static int MULTIPLE_CHOICES = 300;
    public static int MOVED_PERMANENTLY = 301;
    public static int FOUND = 302;
    public static int SEE_OTHER= 303;
    public static int NOT_MODIFIED= 304;
    public static int USE_PROXY = 305;
    public static int SWITCH_PROXY = 306;
    public static int TEMPORARY_REDIRECTED = 307;
    public static int PERMANENT_REDIRECT = 308;

    // 4xx client error
    public static int BAD_REQUEST = 400;
    public static int UNAUTHORIZED = 401;
    public static int PAYMENT_REQUIRED = 402;
    public static int FORBIDDEN = 403;
    public static int NOT_FOUND = 404;
    public static int METHOD_NOT_ALLOWED = 405;
    public static int NOT_ACCEPTIBLE = 406;
    public static int PROXY_AUTH_REQUIRED = 407;
    public static int REQUEST_TIMEOUT = 408;
    public static int CONFLICT = 409;
    public static int GONE = 410;
    public static int LENGTH_REQUIRED = 411;
    public static int PRECONDITION_FAILED = 412;
    public static int PAYLOAD_TOO_LARGE = 413;
    public static int URI_TOO_LONG = 414;
    public static int UNSUPPORTED_MEDIA_TYPE = 415;
    public static int RANGE_NOT_STAISFIABLE = 416;
    public static int EXPECTATION_FAILED = 417;
    public static int IM_TEAPOT = 418;
    public static int MISDIRECTED_REQUEST = 421;
    public static int UNPROCESSABLE_ENTITY = 422;
    public static int LOCKED = 423;
    public static int FAILED_DEPENDENCY = 424;
    public static int UPGRADE_REQUIRED = 426;
    public static int PRECONDITION_REQUIRED = 428;
    public static int TOO_MANY_ERQUESTS = 429;
    public static int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    public static int UNAVAILABLE_FOR_LEGAL_REASONS = 451;


    // 5xx server error
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;
    public static int BAD_GATEWAY = 502;
    public static int SERVICE_UNAVAILABLE = 503;
    public static int GATEWAY_TIMEOUT = 504;
    public static int HTTP_VERSION_NOT_SUPPORTED = 505;
    public static int VARIANT_ALSO_NEGOTIATES = 506;
    public static int INSUFFICIENT_STORAGE = 507;
    public static int LOOP_DETECTED = 508;
    public static int NOT_EXTENDED = 510;
    public static int NETWORK_AUTH_REQUIRED = 511;

    public static HttpResponseCode GetHttpResponse(int code)
    {
        switch(code)
        {
            case 100:
                return HttpResponseCode.CONTINUE;
                
            case 101:
                return HttpResponseCode.SWITCHING_PROTOCOLS;
                
            case 102:
                return HttpResponseCode.PROCESSING;
                
            case 200:
                return HttpResponseCode.OK;
                
            case 201:
                return HttpResponseCode.CREATED;
                
            case 202:
                return HttpResponseCode.ACCEPTED;
                
            case 203:
                return HttpResponseCode.NON_AUTHORITATIVE_INFO;
                
            case 204:
                return HttpResponseCode.RESET_CONETNT;
                
            case 206:
                return HttpResponseCode.PARTIAL_CONTENT;
                
            case 207:
                return HttpResponseCode.MULTI_STATUS;
                
            case 208:
                return HttpResponseCode.ALREADY_REPORTED;
            
            case 226:
                return HttpResponseCode.IM_USED;
            
            case 300:
                return HttpResponseCode.MULTIPLE_CHOICES;
            
            case 301:
                return HttpResponseCode.MOVED_PERMANENTLY;
            
            case 302:
                return HttpResponseCode.FOUND;
            
            case 303:
                return HttpResponseCode.SEE_OTHER;
                
            case 304:
                return HttpResponseCode.NOT_MODIFIED;
            
            case 305:
                return HttpResponseCode.USE_PROXY;
            
            case 306:
                return HttpResponseCode.SWITCH_PROXY;
            
            case 307:
                return HttpResponseCode.TEMPORARY_REDIRECTED;
            
            case 308:
                return HttpResponseCode.PERMANENT_REDIRECT;
            
            case 400:
                return HttpResponseCode.BAD_REQUEST;
            
            case 401:
                return HttpResponseCode.UNAUTHORIZED;
            
            case 402:
                return HttpResponseCode.PAYMENT_REQUIRED;
            
            case 403:
                return HttpResponseCode.FORBIDDEN;
            
            case 404:
                return HttpResponseCode.NOT_FOUND;
            
            case 405:
                return HttpResponseCode.METHOD_NOT_ALLOWED;
            
            case 406:
                return HttpResponseCode.NOT_ACCEPTIBLE;
            
            case 407:
                return HttpResponseCode.PROXY_AUTH_REQUIRED;
            
            case 408:
                return HttpResponseCode.REQUEST_TIMEOUT;
            
            case 409:
                return HttpResponseCode.CONFLICT;
            
            case 410:
                return HttpResponseCode.GONE;
            
            case 411:
                return HttpResponseCode.LENGTH_REQUIRED;
            
            case 412:
                return HttpResponseCode.PRECONDITION_FAILED;
            
            case 413:
                return HttpResponseCode.PAYLOAD_TOO_LARGE;
            
            case 414:
                return HttpResponseCode.URI_TOO_LONG;
            
            case 415:
                return HttpResponseCode.UNSUPPORTED_MEDIA_TYPE;
            
            case 416:
                return HttpResponseCode.RANGE_NOT_STAISFIABLE;
            
            case 417:
                return HttpResponseCode.EXPECTATION_FAILED;
            
            case 418:
                return HttpResponseCode.IM_TEAPOT;
            
            case 421:
                return HttpResponseCode.MISDIRECTED_REQUEST;
            
            case 422:
                return HttpResponseCode.UNPROCESSABLE_ENTITY;
            
            case 423:
                return HttpResponseCode.LOCKED;
            
            case 424:
                return HttpResponseCode.FAILED_DEPENDENCY;
            
            case 426:
                return HttpResponseCode.UPGRADE_REQUIRED;
            
            case 428:
                return HttpResponseCode.PRECONDITION_REQUIRED;
            
            case 429:
                return HttpResponseCode.TOO_MANY_ERQUESTS;
            
            case 431:
                return HttpResponseCode.REQUEST_HEADER_FIELDS_TOO_LARGE;
            
            case 451:
                return HttpResponseCode.UNAVAILABLE_FOR_LEGAL_REASONS;
            
            case 500:
                return HttpResponseCode.INTERNAL_SERVER_ERROR;
            
            case 501:
                return HttpResponseCode.NOT_IMPLEMENTED;
            
            case 502:
                return HttpResponseCode.BAD_GATEWAY;
            
            case 503:
                return HttpResponseCode.SERVICE_UNAVAILABLE;
            
            case 504:
                return HttpResponseCode.GATEWAY_TIMEOUT;
            
            case 505:
                return HttpResponseCode.HTTP_VERSION_NOT_SUPPORTED;
            
            case 506:
                return HttpResponseCode.VARIANT_ALSO_NEGOTIATES;
            
            case 507:
                return HttpResponseCode.INSUFFICIENT_STORAGE;
            
            case 508:
                return HttpResponseCode.LOOP_DETECTED;
            
            case 510:
                return HttpResponseCode.NOT_EXTENDED;
            
            case 511:
                return HttpResponseCode.NETWORK_AUTH_REQUIRED;
            
            default: return HttpResponseCode.UNKNOWN;
        }
    }
}
