package com.halifaxbankbranches.models.halifax.managers;

import com.halifaxbankbranches.models.bank.client.DataClientRequest;
import com.halifaxbankbranches.models.bank.client.DataClientResponse;
import com.halifaxbankbranches.models.halifax.data.Branch;
import com.halifaxbankbranches.models.halifax.data.PostalAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BranchDataManagerTest {
    private static final String MOCK_NAME = "HALIFAX";
    private static final String MOCK_TOWN_NAME = "LONDON";
    private static final String MOCK_COUNTRY = "UK";
    private static final String MOCK_POSTAL_CODE = "L24 9GB";

    @Mock DataClientResponse<Branch> mockDataClientResponse;
    @Mock DataClientRequest<Branch> mockDateClientRequest;
    @Mock ArrayList<Branch> mockBranchesResponse;
    @Mock Branch mockBranchResponse;
    @Mock PostalAddress mockPostalAddress;

    private BranchDataManager branchDataManager;

    @Before
    public void initialize() {
        doAnswer(r -> { mockDataClientResponse.sendResponse(mockBranchesResponse); return null; }).when(mockDateClientRequest).makeRequest(mockDataClientResponse);
        doAnswer(r -> null).when(mockDateClientRequest).stop();
        doAnswer(r -> { mockBranchesResponse.add(mockBranchResponse); return null; }).when(mockDataClientResponse).sendResponse(mockBranchesResponse);
        when(mockBranchResponse.getName()).thenReturn(MOCK_NAME);
        when(mockBranchResponse.getPostalAddress()).thenReturn(mockPostalAddress);
        when(mockPostalAddress.getTownName()).thenReturn(MOCK_TOWN_NAME);
        when(mockPostalAddress.getCountry()).thenReturn(MOCK_COUNTRY);
        when(mockPostalAddress.getPostCode()).thenReturn(MOCK_POSTAL_CODE);

        branchDataManager = new BranchDataManager(mockDateClientRequest);
    }

    @Test
    public void test_whenBranchDataManagerRequestsDataThenDataClientRequestShouldReturnResult() {
        //GIVEN

        //WHEN
        branchDataManager.requestData(mockDataClientResponse);

        //THEN
        assertThat(mockBranchResponse.getName(), is(MOCK_NAME));
        assertThat(mockBranchResponse.getPostalAddress().getTownName(), is(MOCK_TOWN_NAME));
        assertThat(mockBranchResponse.getPostalAddress().getCountry(), is(MOCK_COUNTRY));
        assertThat(mockBranchResponse.getPostalAddress().getPostCode(), is(MOCK_POSTAL_CODE));
    }

    @Test
    public void test_whenBranchDataManagerCallsStopThenDataClientRequestShouldCallStop() {
        //GIVEN

        //WHEN
        branchDataManager.onStop();

        //THEN
        verify(mockDateClientRequest).stop();
    }
}
