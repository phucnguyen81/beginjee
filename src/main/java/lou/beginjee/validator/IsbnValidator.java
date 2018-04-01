package lou.beginjee.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("isbnValidator")
public class IsbnValidator implements Validator {

    /*
     * ISBN general format is at least 13-characters that are either the dash
     * (-), digits (0-9) or the letter x or X.
     */
    private static final Pattern isbnPattern = Pattern
        .compile("(?=[-0-9xX]{13}$)");

    /**
     * Whether input is a generally valid isbn.
     */
    public static boolean isValid(CharSequence isbn) {
        Matcher matcher = isbnPattern.matcher(isbn);
        return !matcher.find();
    }

    @Override
    public void validate(
        FacesContext context,
        UIComponent component,
        Object value) throws ValidatorException
    {
        String componentValue = value.toString();
        if (isValid(componentValue)) return;

        String msg = MessageFormat.format(
            "{0} not a valid isbn format", componentValue);
        FacesMessage facesMessage = new FacesMessage(
            FacesMessage.SEVERITY_ERROR, msg, msg);
        throw new ValidatorException(facesMessage);
    }

}