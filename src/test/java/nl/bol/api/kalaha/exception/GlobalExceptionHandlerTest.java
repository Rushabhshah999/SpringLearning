package nl.bol.api.kalaha.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private Exception exception;

    @Mock
    private HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException;

    @Mock
    private HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException;

    @Mock
    private WebRequest webRequest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void GivenExceptionWhenHandleAllExceptionsCalledThenReturnServerError() {

        when(exception.getLocalizedMessage()).thenReturn("ArrayIndexOutOfBound");
        when(webRequest.getDescription(true)).thenReturn("Array has issue");
        ResponseEntity responseEntity = globalExceptionHandler.handleAllExceptions(exception, webRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertNotNull(responseEntity);

    }

    @Test
    public void GivenExceptionWhenHandleHttpMediaTypeNotSupportedCalledThenReturnServerError() {

        when(httpMediaTypeNotSupportedException.getContentType()).thenReturn(MediaType.APPLICATION_JSON);
        when(httpMediaTypeNotSupportedException.getSupportedMediaTypes()).thenReturn(Arrays.asList(MediaType.APPLICATION_JSON));
        ResponseEntity responseEntity = globalExceptionHandler.handleHttpMediaTypeNotSupported(httpMediaTypeNotSupportedException, null, null, null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        assertNotNull(responseEntity);

    }

    @Test
    public void GivenExceptionWhenHandleHttpRequestMethodNotSupportedCalledThenReturnServerError() {

        when(httpRequestMethodNotSupportedException.getMethod()).thenReturn("POST");
        when(httpRequestMethodNotSupportedException.getLocalizedMessage()).thenReturn("GivenExceptionWhenHandleHttpRequestMethodNotSupportedCalledThenReturnServerError");
        when(httpRequestMethodNotSupportedException.getSupportedMethods()).thenReturn(new String[]{"POST", "GET"});
        ResponseEntity responseEntity = globalExceptionHandler.handleHttpRequestMethodNotSupported(httpRequestMethodNotSupportedException, null, null, null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
        assertNotNull(responseEntity);

    }
}
