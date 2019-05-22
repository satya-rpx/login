/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.login.dbloan.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.login.dbloan.CustomerInfo;
import com.login.dbloan.Customers;
import com.login.dbloan.Property;


/**
 * ServiceImpl object for domain model class CustomerInfo.
 *
 * @see CustomerInfo
 */
@Service("dbloan.CustomerInfoService")
@Validated
public class CustomerInfoServiceImpl implements CustomerInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("dbloan.CustomersService")
    private CustomersService customersService;

    @Lazy
    @Autowired
    @Qualifier("dbloan.PropertyService")
    private PropertyService propertyService;

    @Autowired
    @Qualifier("dbloan.CustomerInfoDao")
    private WMGenericDao<CustomerInfo, String> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<CustomerInfo, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "dbloanTransactionManager")
    @Override
    public CustomerInfo create(CustomerInfo customerInfo) {
        LOGGER.debug("Creating a new CustomerInfo with information: {}", customerInfo);

        CustomerInfo customerInfoCreated = this.wmGenericDao.create(customerInfo);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(customerInfoCreated);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public CustomerInfo getById(String customerinfoId) {
        LOGGER.debug("Finding CustomerInfo by id: {}", customerinfoId);
        return this.wmGenericDao.findById(customerinfoId);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public CustomerInfo findById(String customerinfoId) {
        LOGGER.debug("Finding CustomerInfo by id: {}", customerinfoId);
        try {
            return this.wmGenericDao.findById(customerinfoId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No CustomerInfo found with id: {}", customerinfoId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public List<CustomerInfo> findByMultipleIds(List<String> customerinfoIds, boolean orderedReturn) {
        LOGGER.debug("Finding CustomerInfos by ids: {}", customerinfoIds);

        return this.wmGenericDao.findByMultipleIds(customerinfoIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "dbloanTransactionManager")
    @Override
    public CustomerInfo update(CustomerInfo customerInfo) {
        LOGGER.debug("Updating CustomerInfo with information: {}", customerInfo);

        this.wmGenericDao.update(customerInfo);
        this.wmGenericDao.refresh(customerInfo);

        return customerInfo;
    }

    @Transactional(value = "dbloanTransactionManager")
    @Override
    public CustomerInfo partialUpdate(String customerinfoId, Map<String, Object>customerInfoPatch) {
        LOGGER.debug("Partially Updating the CustomerInfo with id: {}", customerinfoId);

        CustomerInfo customerInfo = getById(customerinfoId);

        try {
            ObjectReader customerInfoReader = this.objectMapper.reader().forType(CustomerInfo.class).withValueToUpdate(customerInfo);
            customerInfo = customerInfoReader.readValue(this.objectMapper.writeValueAsString(customerInfoPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", customerInfoPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        customerInfo = update(customerInfo);

        return customerInfo;
    }

    @Transactional(value = "dbloanTransactionManager")
    @Override
    public CustomerInfo delete(String customerinfoId) {
        LOGGER.debug("Deleting CustomerInfo with id: {}", customerinfoId);
        CustomerInfo deleted = this.wmGenericDao.findById(customerinfoId);
        if (deleted == null) {
            LOGGER.debug("No CustomerInfo found with id: {}", customerinfoId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), CustomerInfo.class.getSimpleName(), customerinfoId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "dbloanTransactionManager")
    @Override
    public void delete(CustomerInfo customerInfo) {
        LOGGER.debug("Deleting CustomerInfo with {}", customerInfo);
        this.wmGenericDao.delete(customerInfo);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public Page<CustomerInfo> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all CustomerInfos");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public Page<CustomerInfo> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all CustomerInfos");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service dbloan for table CustomerInfo to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service dbloan for table CustomerInfo to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public Page<Customers> findAssociatedCustomerses(String aadhaarCard, Pageable pageable) {
        LOGGER.debug("Fetching all associated customerses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("customerInfo.aadhaarCard = '" + aadhaarCard + "'");

        return customersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "dbloanTransactionManager")
    @Override
    public Page<Property> findAssociatedProperties(String aadhaarCard, Pageable pageable) {
        LOGGER.debug("Fetching all associated properties");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("customerInfo.aadhaarCard = '" + aadhaarCard + "'");

        return propertyService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service CustomersService instance
     */
    protected void setCustomersService(CustomersService service) {
        this.customersService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service PropertyService instance
     */
    protected void setPropertyService(PropertyService service) {
        this.propertyService = service;
    }

}