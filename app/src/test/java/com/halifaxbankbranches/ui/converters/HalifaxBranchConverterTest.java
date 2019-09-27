package com.halifaxbankbranches.ui.converters;

import com.halifaxbankbranches.models.halifax.data.Branch;
import com.halifaxbankbranches.models.halifax.data.ContactInfo;
import com.halifaxbankbranches.models.halifax.data.GeoLocation;
import com.halifaxbankbranches.models.halifax.data.GeographicCoordinates;
import com.halifaxbankbranches.models.halifax.data.PostalAddress;
import com.halifaxbankbranches.ui.data.BranchModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HalifaxBranchConverterTest {
    private static final String MOCK_NAME = "PRESCOT";
    private static final String MOCK_CONTACT_TYPE = "Phone";
    private static final String MOCK_CONTACT_CONTENT = "+44-151633000";
    private static final String MOCK_ADDRESS_LINE1 = "HUNTS CROSS SHOPPING CENTRE UNIT D & E";
    private static final String MOCK_TOWN_NAME = "LONDON";
    private static final String MOCK_COUNTRY = "UK";
    private static final String MOCK_POST_CODE = "L34 9GB";
    private static final String MOCK_LATITUDE = "37.422";
    private static final String MOCK_LONGITUDE = "-122.084";

    private ArrayList<Branch> branchesResponse;
    private ContactInfo[] contactInfoArray;
    private String[] addressLines;

    @Mock Branch mockBranchResponse;
    @Mock ContactInfo mockContactInfo;
    @Mock PostalAddress mockPostalAddress;
    @Mock GeoLocation mockGeoLocation;
    @Mock GeographicCoordinates mockGeographicCoordinates;

    @Before
    public void initialize() {
        branchesResponse = new ArrayList<>();
        branchesResponse.add(mockBranchResponse);
        contactInfoArray = new ContactInfo[1];
        contactInfoArray[0] = mockContactInfo;
        addressLines = new String[1];
        addressLines[0] = MOCK_ADDRESS_LINE1;

        when(mockBranchResponse.getName()).thenReturn(MOCK_NAME);
        when(mockBranchResponse.getContactInfo()).thenReturn(contactInfoArray);
        when(mockContactInfo.getContactType()).thenReturn(MOCK_CONTACT_TYPE);
        when(mockContactInfo.getContactContent()).thenReturn(MOCK_CONTACT_CONTENT);
        when(mockBranchResponse.getPostalAddress()).thenReturn(mockPostalAddress);
        when(mockPostalAddress.getAddressLine()).thenReturn(addressLines);
        when(mockPostalAddress.getTownName()).thenReturn(MOCK_TOWN_NAME);
        when(mockPostalAddress.getCountry()).thenReturn(MOCK_COUNTRY);
        when(mockPostalAddress.getPostCode()).thenReturn(MOCK_POST_CODE);
        when(mockPostalAddress.getGeoLocation()).thenReturn(mockGeoLocation);
        when(mockGeoLocation.getGeographicCoordinates()).thenReturn(mockGeographicCoordinates);
        when(mockGeographicCoordinates.getLatitude()).thenReturn(MOCK_LATITUDE);
        when(mockGeographicCoordinates.getLongitude()).thenReturn(MOCK_LONGITUDE);
    }

    @Test
    public void test_whenHalifaxBranchConverterCallsFromHalifaxBranchesThenItShouldReturnArrayListOfBranchModels() {
        //GIVEN

        //WHEN
        ArrayList<BranchModel> result = HalifaxBranchConverter.fromHalifaxBranches(branchesResponse);

        //THEN
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is(MOCK_NAME));
        assertThat(result.get(0).getContactInfoModel().length, is(1));
        assertThat(result.get(0).getContactInfoModel()[0].getContactType(), is(MOCK_CONTACT_TYPE));
        assertThat(result.get(0).getContactInfoModel()[0].getContactContent(), is(MOCK_CONTACT_CONTENT));
        assertThat(result.get(0).getPostalAddressModel().getAddressLine().length, is(1));
        assertThat(result.get(0).getPostalAddressModel().getAddressLine()[0], is(MOCK_ADDRESS_LINE1));
        assertThat(result.get(0).getPostalAddressModel().getTownName(), is(MOCK_TOWN_NAME));
        assertThat(result.get(0).getPostalAddressModel().getCountry(), is(MOCK_COUNTRY));
        assertThat(result.get(0).getPostalAddressModel().getPostCode(), is(MOCK_POST_CODE));
        assertThat(result.get(0).getPostalAddressModel().getGeoLocationModel().getGeographicCoordinatesModel().getLatitude(),
                is(MOCK_LATITUDE));
        assertThat(result.get(0).getPostalAddressModel().getGeoLocationModel().getGeographicCoordinatesModel().getLongitude(),
                is(MOCK_LONGITUDE));
    }
}
