package anseev.demo.service;

import anseev.demo.model.MathResults;
import anseev.demo.repository.MathResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MathResultsService implements ServiceTemplate<MathResults, Long> {

    private final MathResultsRepository repository;

    @Override
    public MathResults save(MathResults obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(MathResults obj) {
        repository.delete(obj);
    }

    @Override
    public MathResults update(MathResults obj) {
        return repository.save(obj);
    }

    @Override
    public MathResults get(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<MathResults> getAll() {
        return repository.findAll();
    }

}
