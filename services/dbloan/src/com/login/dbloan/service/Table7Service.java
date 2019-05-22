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

import com.login.dbloan.Table7;

/**
 * Service object for domain model class {@link Table7}.
 */
public interface Table7Service {

    /**
     * Creates a new Table7. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Table7 if any.
     *
     * @param table7 Details of the Table7 to be created; value cannot be null.
     * @return The newly created Table7.
     */
    Table7 create(@Valid Table7 table7);


	/**
     * Returns Table7 by given id if exists.
     *
     * @param table7Id The id of the Table7 to get; value cannot be null.
     * @return Table7 associated with the given table7Id.
	 * @throws EntityNotFoundException If no Table7 is found.
     */
    Table7 getById(Integer table7Id);

    /**
     * Find and return the Table7 by given id if exists, returns null otherwise.
     *
     * @param table7Id The id of the Table7 to get; value cannot be null.
     * @return Table7 associated with the given table7Id.
     */
    Table7 findById(Integer table7Id);

	/**
     * Find and return the list of Table7s by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param table7Ids The id's of the Table7 to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Table7s associated with the given table7Ids.
     */
    List<Table7> findByMultipleIds(List<Integer> table7Ids, boolean orderedReturn);


    /**
     * Updates the details of an existing Table7. It replaces all fields of the existing Table7 with the given table7.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Table7 if any.
     *
     * @param table7 The details of the Table7 to be updated; value cannot be null.
     * @return The updated Table7.
     * @throws EntityNotFoundException if no Table7 is found with given input.
     */
    Table7 update(@Valid Table7 table7);


    /**
     * Partially updates the details of an existing Table7. It updates only the
     * fields of the existing Table7 which are passed in the table7Patch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Table7 if any.
     *
     * @param table7Id The id of the Table7 to be deleted; value cannot be null.
     * @param table7Patch The partial data of Table7 which is supposed to be updated; value cannot be null.
     * @return The updated Table7.
     * @throws EntityNotFoundException if no Table7 is found with given input.
     */
    Table7 partialUpdate(Integer table7Id, Map<String, Object> table7Patch);

    /**
     * Deletes an existing Table7 with the given id.
     *
     * @param table7Id The id of the Table7 to be deleted; value cannot be null.
     * @return The deleted Table7.
     * @throws EntityNotFoundException if no Table7 found with the given id.
     */
    Table7 delete(Integer table7Id);

    /**
     * Deletes an existing Table7 with the given object.
     *
     * @param table7 The instance of the Table7 to be deleted; value cannot be null.
     */
    void delete(Table7 table7);

    /**
     * Find all Table7s matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Table7s.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Table7> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Table7s matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Table7s.
     *
     * @see Pageable
     * @see Page
     */
    Page<Table7> findAll(String query, Pageable pageable);

    /**
     * Exports all Table7s matching the given input query to the given exportType format.
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
     * Exports all Table7s matching the given input query to the given exportType format.
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
     * Retrieve the count of the Table7s in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Table7.
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


}