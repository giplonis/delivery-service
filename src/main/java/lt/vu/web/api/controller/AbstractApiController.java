package lt.vu.web.api.controller;

import lt.vu.web.api.dto.ApiResourceDTO;

public class AbstractApiController<T> {

    protected ApiResourceDTO<T> getApiResponse(T data) {
        return new ApiResourceDTO<T>(data);
    }
}
