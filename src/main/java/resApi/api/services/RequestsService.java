package resApi.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import resApi.api.entities.Application;
import resApi.api.repositories.ApplicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestsService {
    private final ApplicationRepository appRepository;


    public void addRequest(Application request){
        appRepository.save(request);
    }

    public List<Application> getRequests(){
        List<Application> requests = appRepository.findAll();
        return requests;
    }

    public Application getRequest(Long id){
        Application request = appRepository.findById(id).orElseThrow();
        return request;
    }

    public void handleRequest(Long id){
        Application request = appRepository.findById(id).orElseThrow();
        request.setHandled(true);
        appRepository.save(request);
    }

    public void delete(Long id){
        appRepository.deleteById(id);
    }

    public List<Application> notHandled(){
        List<Application> requests = appRepository.findByHandledFalse();
        return requests;
    }

    public List<Application> handled(){
        List<Application> requests = appRepository.findByHandledTrue();
        return requests;
    }
}
