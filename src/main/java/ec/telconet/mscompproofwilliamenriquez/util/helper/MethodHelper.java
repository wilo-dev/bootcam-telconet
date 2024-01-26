package ec.telconet.mscompproofwilliamenriquez.util.helper;

import org.springframework.http.HttpStatus;

public class MethodHelper {

    public static HttpStatus selectStatus(Integer code) {
        HttpStatus status = null;
        switch (code) {
            case 200: {
                status = HttpStatus.OK;
                break;
            }
            case 201: {
                status = HttpStatus.CREATED;
                break;
            }
            case 400: {
                status = HttpStatus.BAD_REQUEST;
                break;
            }
            case 404: {
                status = HttpStatus.NOT_FOUND;
                break;
            }
            case 500: {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            }
        }
        return status;
    }
}
