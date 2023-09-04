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
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        //given
        Set<Visit> visits = Set.of(
                new Visit(1L, LocalDate.now()),
                new Visit(2L, LocalDate.of(2022, 10, 19)));
        given(visitRepository.findAll()).willReturn(visits);
        //when
        Set<Visit> foundVisits = visitSDJpaService.findAll();
        //then
        then(visitRepository).should(atLeastOnce()).findAll();
        assertEquals(2, foundVisits.size());
    }

    @Test
    void findById() {
        //given
        Visit visit = new Visit(1L, LocalDate.now());
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));
        //when
        Visit fVisit = visitSDJpaService.findById(1L);
        //then
        then(visitRepository).should(atLeastOnce()).findById(anyLong());
        assertNotNull(fVisit);

    }

    @Test
    void save() {
        //given
        Visit visit = new Visit(1L, LocalDate.now());
        given(visitRepository.save(visit)).willReturn(visit);
        //when
        Visit sVisit = visitSDJpaService.save(visit);
        //then
        then(visitRepository).should(atLeastOnce()).save(visit);
        assertNotNull(sVisit);
    }

    @Test
    void delete() {
        //given
        Visit visit = new Visit(1L, LocalDate.now());
        //when
        visitSDJpaService.delete(visit);
        //then
        then(visitRepository).should(atLeastOnce()).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //given
        long serviceId = 1L;
        //when
        visitSDJpaService.deleteById(serviceId);
        //then
        then(visitRepository).should(atLeastOnce()).deleteById(anyLong());
    }
}