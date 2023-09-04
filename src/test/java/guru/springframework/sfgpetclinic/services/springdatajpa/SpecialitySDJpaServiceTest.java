package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService specialtyService;


    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        specialtyService.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();
        Mockito.when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = specialtyService.findById(1l);
        assertNotNull(foundSpeciality);
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void deleteById() {
        specialtyService.deleteById(1l);
        specialtyService.deleteById(1l);
        verify(specialtyRepository, times(2)).deleteById(1l);
    }


    @Test
    void deleteByIdAtleast() {
        specialtyService.deleteById(1l);
        specialtyService.deleteById(1l);
        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        specialtyService.deleteById(1l);
        specialtyService.deleteById(1l);
        verify(specialtyRepository, atMost(5)).deleteById(1l);
    }


    @Test
    void deleteByIdAtNever() {
        specialtyService.deleteById(1l);
        specialtyService.deleteById(1l);
        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        verify(specialtyRepository, never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        specialtyService.delete(new Speciality());
    }
}