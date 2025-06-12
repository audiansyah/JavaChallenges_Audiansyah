package com.codeid.axaTest.model.dto;

import com.codeid.axaTest.model.enumeration.EnumStatus;

public record PermissionDto(Long permissionId, EnumStatus permissionType, Long roleId) {}

