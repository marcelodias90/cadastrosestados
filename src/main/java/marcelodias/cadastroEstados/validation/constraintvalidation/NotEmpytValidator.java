package marcelodias.cadastroEstados.validation.constraintvalidation;

import marcelodias.cadastroEstados.validation.NotEmpytList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmpytValidator implements ConstraintValidator<NotEmpytList, List>
{
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext)
    {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmpytList constraintAnnotation)
    {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
