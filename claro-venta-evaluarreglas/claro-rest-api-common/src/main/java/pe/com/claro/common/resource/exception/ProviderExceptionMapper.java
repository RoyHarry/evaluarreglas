/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pe.com.claro.common.resource.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import pe.com.claro.common.resource.util.ApiResponse;

@Provider
public class ProviderExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderExceptionMapper.class);
	private static final String ERROR = "ERROR: [";
	private static final String LLAVE_APERTURA = "] - [";
	private static final String LLAVE_CERRADURA = "] ";	

	public Response toResponse(Exception exception) {
		if (exception instanceof javax.ws.rs.WebApplicationException) {
			javax.ws.rs.WebApplicationException e = (javax.ws.rs.WebApplicationException) exception;
			LOG.error("ERROR:", WebApplicationException.class + LLAVE_APERTURA + e.getMessage() + LLAVE_CERRADURA, e);
			return Response.status(e.getResponse().getStatus())
					.entity(new ApiResponse(e.getResponse().getStatus(), exception.getMessage())).build();
		} else if (exception instanceof com.fasterxml.jackson.core.JsonParseException) {
			LOG.error(ERROR + JsonParseException.class + LLAVE_APERTURA + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(Status.BAD_REQUEST).entity(new ApiResponse(ApiResponse.ERROR, "Entrada incorrecta"))
					.build();
		} else if (exception instanceof NotFoundException) {
			LOG.error(ERROR + WebApplicationException.class + LLAVE_APERTURA + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(((NotFoundException) exception).getStatus())
					.entity(new ErrorMessage((NotFoundException) exception)).type(MediaType.APPLICATION_JSON).build();
		} else if (exception instanceof BadRequestException) {
			LOG.error(ERROR + BadRequestException.class + LLAVE_APERTURA + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(((BadRequestException) exception).getStatus())
					.entity(new ErrorMessage((BadRequestException) exception)).type(MediaType.APPLICATION_JSON).build();
		} else if (exception instanceof ApiException) {
			LOG.error(ERROR + ApiException.class + LLAVE_APERTURA + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(((ApiException) exception).getStatus())
					.entity(new ErrorMessage((ApiException) exception)).type(MediaType.APPLICATION_JSON).build();
		} else if (exception instanceof BadUsageException) {
			LOG.error(ERROR + BadUsageException.class + LLAVE_APERTURA + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(((BadUsageException) exception).getStatus())
					.entity(new ErrorMessage((BadUsageException) exception)).type(MediaType.APPLICATION_JSON).build();
		} else {
			LOG.error(ERROR + exception.getMessage() + LLAVE_CERRADURA, exception);
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ApiResponse(ApiResponse.ERROR, "Sucedió algo inesperado: " + exception.getMessage()))
					.build();
		}
	}

}