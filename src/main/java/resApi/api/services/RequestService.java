package resApi.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import resApi.api.entities.ApplicationRequest;
import resApi.api.entities.Courses;
import resApi.api.entities.Operators;
import resApi.api.repositories.CoursesRepository;
import resApi.api.repositories.OperatorsRepository;
import resApi.api.repositories.RequestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final CoursesRepository coursesRepository;
    private final OperatorsRepository operatorsRepository;

    public List<ApplicationRequest> getAll(){
        return requestRepository.findAll();
    }

    public ApplicationRequest addNewReq(ApplicationRequest newRequest, Long courseId){
        Courses course = coursesRepository.findById(courseId).orElseThrow();
        newRequest.setCourse(course);
        return requestRepository.save(newRequest);
    }

    public ApplicationRequest getRequestById(Long id){
        ApplicationRequest request = requestRepository.findById(id).orElseThrow();

        if(request.getOperators().isEmpty()){
            request.setHandled(false);
        }
        return request;
    }

    public ApplicationRequest update(Long id, ArrayList<Long> operatorIds){
        ApplicationRequest request = requestRepository.findById(id).orElseThrow();
        List<Operators> operators = new ArrayList<>();

        operatorIds.forEach(operatorId -> operators.add(operatorsRepository.findById(operatorId).orElseThrow()));
        request.setOperators(operators);
        request.setHandled(true);

        operatorIds.forEach(operatorId -> (operatorsRepository.findById(operatorId).orElseThrow()).getRequests().add(request));
        operatorsRepository.saveAll(operators);

        return requestRepository.save(request);
    }

    public boolean deleteOperatorId(Long operatorId, Long requestId){
        ApplicationRequest request = requestRepository.findById(requestId).orElseThrow();
        Operators operator = operatorsRepository.findById(operatorId).orElseThrow();

        boolean removed = request.getOperators().remove(operator);
        requestRepository.save(request);

        operator.getRequests().remove(request);
        operatorsRepository.save(operator);

        if(removed){
            return true;
        }else{
            return false;
        }
    }

}
