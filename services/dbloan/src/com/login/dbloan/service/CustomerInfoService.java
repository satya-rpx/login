/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.login.dbloan.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
 * Service object for domain model class {@link CustomerInfo}.
 */
public interface CustomerInfoService {

    /**
     * Creates a new CustomerInfo. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CustomerInfo if any.
     *
     * @param customerInfo Details of the CustomerInfo to be created; value cannot be null.
     * @return The newly created CustomerInfo.
     */
    CustomerInfo create(@Valid CustomerInfo customerInfo);


	/**
     * Returns CustomerInfo by given id if exists.
     *
     * @param customerinfoId The id of the CustomerInfo to get; value cannot be null.
     * @return CustomerInfo associated with the given customerinfoId.
	 * @throws EntityNotFoundException If no CustomerInfo is found.
     */
    CustomerInfo getById(String customerinfoId);

    /**
     * Find and return the CustomerInfo by given id if exists, returns null otherwise.
     *
     * @param customerinfoId The id of the CustomerInfo to get; value cannot be null.
     * @return CustomerInfo associated with the given customerinfoId.
     */
    CustomerInfo findById(String customerinfoId);

	/**
     * Find and return the list of CustomerInfos by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param customerinfoIds The id's of the CustomerInfo to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return CustomerInfos associated with the given customerinfoIds.
     */
    List<CustomerInfo> findByMultipleIds(List<String> customerinfoIds, boolean orderedReturn);


    /**
     * Updates the details of an existing CustomerInfo. It replaces all fields of the existing CustomerInfo with the given customerInfo.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CustomerInfo if any.
     *
     * @param customerInfo The details of the CustomerInfo to be updated; value cannot be null.
     * @return The updated CustomerInfo.
     * @throws EntityNotFoundException if no CustomerInfo is found with given input.
     */
    CustomerInfo update(@Valid CustomerInfo customerInfo);


    /**
     * Partially updates the details of an existing CustomerInfo. It updates only the
     * fields of the existing CustomerInfo which are passed in the customerInfoPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CustomerInfo if any.
     *
     * @param customerinfoId The id of the CustomerInfo to be deleted; value cannot be null.
     * @param customerInfoPatch The partial data of CustomerInfo which is supposed to be updated; value cannot be null.
     * @return The updated CustomerInfo.
     * @throws EntityNotFoundException if no CustomerInfo is found with given input.
     */
    CustomerInfo partialUpdate(String customerinfoId, Map<String, Object> customerInfoPatch);

    /**
     * Deletes an existing CustomerInfo with the given id.
     *
     * @param customerinfoId The id of the CustomerInfo to be deleted; value cannot be null.
     * @return The deleted CustomerInfo.
     * @throws EntityNotFoundException if no CustomerInfo found with the given id.
     */
    CustomerInfo delete(String customerinfoId);

    /**
     * Deletes an existing CustomerInfo with the given object.
     *
     * @param customerInfo The instance of the CustomerInfo to be deleted; value cannot be null.
     */
    void delete(CustomerInfo customerInfo);

    /**
     * Find all CustomerInfos matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CustomerInfos.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<CustomerInfo> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all CustomerInfos matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CustomerInfos.
     *
     * @see Pageable
     * @see Page
     */
    Page<CustomerInfo> findAll(String query, Pageable pageable);

    /**
     * Exports all CustomerInfos matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all CustomerInfos matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the CustomerInfos in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the CustomerInfo.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated customerses for given CustomerInfo id.
     *
     * @param aadhaarCard value of aadhaarCard; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Customers instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Customers> findAssociatedCustomerses(String aadhaarCard, Pageable pageable);

    /*
     * Returns the associated properties for given CustomerInfo id.
     *
     * @param aadhaarCard value of aadhaarCard; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Property instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Property> findAssociatedProperties(String aadhaarCard, Pageable pageable);

}