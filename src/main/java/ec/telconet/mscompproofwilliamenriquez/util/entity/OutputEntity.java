package ec.telconet.mscompproofwilliamenriquez.util.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.helper.MethodHelper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Data
public class OutputEntity<T> {
    @JsonIgnore
    private HttpStatus code;
    private ArrayList<String> message = new ArrayList<>();
    private Boolean error = false;
    private T data;

    public OutputEntity<T> ok(Integer code, String mensaje, T data) {
        this.code = MethodHelper.selectStatus(code);
        this.message.add(mensaje);
        this.data = data;
        return this;
    }

    public OutputEntity<T> ok(Integer code, ArrayList<String> mensaje, T data) {
        this.code = MethodHelper.selectStatus(code);
        this.message = mensaje;
        this.data = data;
        return this;
    }

    public OutputEntity<T> error(Integer code, String mensaje, T data) {
        this.code = MethodHelper.selectStatus(code);
        this.message.add(mensaje);
        this.error = true;
        this.data = data;
        return this;
    }

    public OutputEntity<T> error(Integer code, ArrayList<String> mensaje, T data) {
        this.code = MethodHelper.selectStatus(code);
        this.message = mensaje;
        this.error = true;
        this.data = data;
        return this;
    }


    public OutputEntity<T> error() {
        this.code = MethodHelper.selectStatus(MessageEnum.INTERNAL_ERROR.getCode());
        this.message.add(MessageEnum.INTERNAL_ERROR.getMensaje());
        this.error = true;
        this.data = null;
        return this;
    }
}
