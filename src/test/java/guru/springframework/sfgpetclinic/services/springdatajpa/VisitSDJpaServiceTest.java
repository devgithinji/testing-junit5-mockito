package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        Set<Visit> visits = Set.of(
                new Visit(1L, LocalDate.now()),
                new Visit(2L, LocalDate.of(2022, 10, 19)));
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = visitSDJpaService.findAll();
        verify(visitRepository, atLeastOnce()).findAll();
        assertEquals(2, foundVisits.size());
    }

    @Test
    void findById() {
        Visit visit = new Visit(1L, LocalDate.now());
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        Visit fVisit = visitSDJpaService.findById(1L);
        verify(visitRepository, atLeastOnce()).findById(anyLong());
        assertNotNull(fVisit);


    }

    @Test
    void save() {
        Visit visit = new Visit(1L, LocalDate.now());
        when(visitRepository.save(visit)).thenReturn(visit);
        Visit sVisit = visitSDJpaService.save(visit);
        verify(visitRepository, atLeastOnce()).save(visit);
        assertNotNull(sVisit);
    }

    @Test
    void delete() {
        Visit visit = new Visit(1L, LocalDate.now());
        visitSDJpaService.delete(visit);
        verify(visitRepository, atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);
        verify(visitRepository, atLeastOnce()).deleteById(anyLong());
    }
}