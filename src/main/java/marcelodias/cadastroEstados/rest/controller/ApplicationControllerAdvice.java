package marcelodias.cadastroEstados.rest.controller;

import marcelodias.cadastroEstados.exception.ExistenteException;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.rest.ApiErrors;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice
{
    @ExceptionHandler(RegradeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegradeNegocioException ex)
    {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }
    @ExceptionHandler(ExistenteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrors handleExistenteException(ExistenteException ex)
    {
        String mensagemErro = ex.getMessage();
        return  new ApiErrors(mensagemErro);
    }
}
