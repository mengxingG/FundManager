//package com.example.fundmanager.controller;
//
//
//import com.example.fundmanager.dao.SecurityRepository;
//import com.example.fundmanager.entity.Security;
//import com.example.fundmanager.exception.*;
//import com.example.fundmanager.service.SecurityService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest
//public class SecurityControllerTests {
//    @MockBean
//    SecurityController securityController;
//
//    @MockBean
//    SecurityService securityService;
//
//    @MockBean
//    SecurityRepository securityRepository;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    List<Security> defaultSecurities = List.of(
//            new Security(1L, "IBM"),
//            new Security(2L, "Microsoft")
//    );
//
//    @Test
//    public void testGetAllSecuritiesSuccess() throws Exception{
//        when(securityService.getSecurities()).thenReturn(defaultSecurities);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/security")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetSecuritySuccess() throws Exception{
//        when(securityService.getSecurity(1L)).thenReturn(defaultSecurities.get(0));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/security/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetSecurityNotFound() throws Exception{
//        when(securityService.getSecurity(1L)).thenThrow(SecurityNotFoundException.class);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/security/1")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testAddSecuritySuccess() throws Exception{
//        String json = "{\n" +
//                "    \"symbol\": \"some symbol\",\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/api/security")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isOk());
//
//        verify(securityService).addSecurity(any(Security.class));
//    }
//
//    @Test
//    public void testAddSecurityAlreadyInUse() throws Exception{
//        String json = "{\n" +
//                "    \"symbol\": \"IBM\",\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/api/security")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isBadRequest());
//
//        verify(securityService).addSecurity(any(Security.class));
//    }
//
//    @Test
//    public void testUpdateSecuritySuccess() throws Exception{
//        String json = "{\n" +
//                "    \"securityId\": 1,\n" +
//                "    \"symbol\": \"Oracle\",\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .put("/api/security/1")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//        mockMvc.perform(request).andExpect(status().isOk());
//
//        verify(securityService).updateSecurity(anyLong(),any(Security.class));
//    }
//
//    @Test
//    public void testUpdateSecurityIdNotMatching() throws Exception{
//        doThrow(SecurityIdNotMatchingException.class).when(securityService).updateSecurity(anyLong(), any(Security.class));
//
//        String json = "{\n" +
//                "    \"securityId\": 1,\n" +
//                "    \"symbol\": \"Oracle\",\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .put("/api/security/2")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isBadRequest());
//        verify(securityService).updateSecurity(anyLong(), any(Security.class));
//    }
//
//    @Test
//    public void testUpdateAlreadyInUse() throws Exception{
//        doThrow(SecurityAlreadyInUseException.class).when(securityService).updateSecurity(anyLong(), any(Security.class));
//
//        String json = "{\n" +
//                "    \"securityId\": 1,\n" +
//                "    \"symbol\": \"IBM\",\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .put("/api/Security/1")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isBadRequest());
//        verify(securityService).updateSecurity(anyLong(), any(Security.class));
//    }
//
//    @Test
//    public void testUpdateIllegalUpdatedSecurity() throws Exception{
//        doThrow(IllegalUpdatedSecurityException.class).when(securityService).updateSecurity(anyLong(), any(Security.class));
//
//        String json = "{\n" +
//                "    \"securityId\": 1,\n" +
//                "    \"symbol\": null,\n" +
//                "}";
//        RequestBuilder request = MockMvcRequestBuilders
//                .put("/api/Security/1")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isBadRequest());
//        verify(securityService).updateSecurity(anyLong(), any(Security.class));
//    }
//
//    @Test
//    public void testRemoveSecuritySuccess() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/security/1")).andExpect(status().isOk());
//        verify(securityService).removeSecurity(anyLong());
//    }
//
//    @Test
//    public void testRemoveNotSecurity() throws Exception{
//        doThrow(SecurityNotFoundException.class).when(securityService).removeSecurity(anyLong());
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/security/3")).andExpect(status().isNotFound());
//        verify(securityService).removeSecurity(anyLong());
//    }
//
//}
