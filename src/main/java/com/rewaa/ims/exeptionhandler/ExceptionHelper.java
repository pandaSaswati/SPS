package com.rewaa.ims.exeptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.rewaa.ims.utils.CommonResponse;

@ControllerAdvice
public class ExceptionHelper
{

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(value =
	{
		InvalidInputException.class
	})

	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex)
	{

		LOGGER.error("Invalid Input Exception: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value =
	{
		Unauthorized.class,
	})

	public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex)
	{

		LOGGER.error("Unauthorized Exception: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(value =
	{
		UnAuthorizedException.class
	})

	public ResponseEntity<Object> handleUnauthorizedException(UnAuthorizedException ex)
	{

		LOGGER.error("Unauthorized Exception: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(value =
	{
		NullPointerException.class
	})

	public ResponseEntity<Object> handleNullException(NullPointerException ex)
	{

		LOGGER.error("NullPointerException: ", ex);
		return new ResponseEntity<Object>(getErrorCode("Null pointer exception in server", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value =
	{
		DataAccessException.class
	})

	public ResponseEntity<Object> handleDataAccessException(DataAccessException ex)
	{

		LOGGER.error("DataAccessException: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value =
		{
			HttpMessageNotReadableException.class
		})

		public ResponseEntity<Object> handleDataAccessException(HttpMessageNotReadableException ex)
		{

			LOGGER.error("HttpMessageNotReadableException: ", ex);
			return new ResponseEntity<Object>(getErrorCode("Body message not able to read...", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	@ExceptionHandler(value =
	{
		SQLException.class
	})

	public ResponseEntity<Object> handleSQLException(SQLException ex)
	{

		LOGGER.error("SQL Exception: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value =
	{
		Exception.class
	})

	public ResponseEntity<Object> handleException(Exception ex)
	{

		LOGGER.error("Exception: ", ex);
		return new ResponseEntity<Object>(getErrorCode(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private CommonResponse getErrorCode(String internalErrorMessage, HttpStatus internalServerError)
	{
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setErrorcode(internalServerError.getReasonPhrase());
		commonResponse.setInternalErrorMessage(internalErrorMessage);
		return commonResponse;
	}
}
