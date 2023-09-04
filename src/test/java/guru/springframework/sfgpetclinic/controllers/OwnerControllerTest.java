package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private final String DEFAULT_PAGE = "owners/createOrUpdateOwnerForm";
    private final String REDIRECT_PAGE = "redirect:/owners/";
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;
    @Mock
    BindingResult bindingResult;

    Owner owner;


    @BeforeEach
    void setUp() {
        owner = new Owner(1L, "John", "Doe");
    }


    @Test
    void processFindFormWildCardString() {
        //given - none
        List<Owner> owners = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(owners);
        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        //then
        assertTrue("%Doe%".equalsIgnoreCase(captor.getValue()));

    }

    @Test
    void processCreationFormErrorsTest() {
        //given
        //simulate errors
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String view = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertEquals(DEFAULT_PAGE, view);
        then(ownerService).should(never()).save(any());
    }

    @Test
    void processCreationFormNoErrorsTest() {
        //given
        //simulate no errors
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(owner)).willReturn(owner);
        //when
        String view = ownerController.processCreationForm(owner, bindingResult);
        //then
        assertEquals(REDIRECT_PAGE + owner.getId(), view);
        then(ownerService).should().save(any());
    }
}