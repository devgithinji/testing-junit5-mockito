package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService specialtyService;


    @Test
    void testDeleteByObject() {
        //given
        Speciality speciality = new Speciality();
        //when
        specialtyService.delete(speciality);
        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }


    @Test
    void findByIdTest() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
        //when
        Speciality fSpeciality = specialtyService.findById(1L);
        //then
        assertNotNull(fSpeciality);
        then(specialtyRepository).should().findById(anyLong());
    }

    @Test
    void deleteById() {
        //given - none
        //when
        specialtyService.deleteById(1L);
        specialtyService.deleteById(1L);
        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }


    @Test
    void deleteByIdAtleast() {
        //given - none
        //when
        specialtyService.deleteById(1L);
        specialtyService.deleteById(1L);
        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        //given - none
        //when
        specialtyService.deleteById(1L);
        specialtyService.deleteById(1L);
        //then
        then(specialtyRepository).should(atMost(5)).deleteById(1l);
    }


    @Test
    void deleteByIdAtNever() {
        //given - none
        //when
        specialtyService.deleteById(1L);
        specialtyService.deleteById(1L);
        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        //given - none
        //when
        specialtyService.delete(new Speciality());
        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }
}