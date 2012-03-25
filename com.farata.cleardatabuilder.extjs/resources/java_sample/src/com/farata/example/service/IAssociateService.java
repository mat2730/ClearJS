package com.farata.example.service;

import java.util.List;

import com.farata.example.dto.AssociateDTO;

import clear.cdb.extjs.annotations.CX_JSFillMethod;
import clear.cdb.extjs.annotations.CX_JSGenerateSample;
import clear.cdb.extjs.annotations.CX_JSGenerateStore;
import clear.cdb.extjs.annotations.CX_JSService;

@CX_JSService
public interface IAssociateService {
	@CX_JSGenerateSample
	@CX_JSGenerateStore
	@CX_JSFillMethod(autoSyncEnabled = true)
	List<AssociateDTO> getAssociates(Integer companyId);
}