package com.huyan.service.house;


import com.huyan.entity.SubwayStation;
import com.huyan.entity.SupportAddress;
import com.huyan.responsitory.SubwayRepository;
import com.huyan.responsitory.SubwayStationRepository;
import com.huyan.responsitory.SupportAddressRepository;
import com.huyan.service.ServiceMultiResult;
import com.huyan.service.ServiceResult;
import com.huyan.service.search.BaiduMapLocation;
import com.huyan.web.dto.SubwayDTO;
import com.huyan.web.dto.SubwayStationDTO;
import com.huyan.web.dto.SupportAddressDTO;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 胡琰
 * @date 2019/4/17 20:30
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private SubwayRepository subwayRepository;

    private static final String BAIDU_MAP_KEY = "mupRdBgpv91A3YwQBwuatO4pgD0RzKGE";

    private static final String BAIDU_MAP_GEOCONV_API = "http://api.map.baidu.com/geocoder/v3/?";

    private static final String LBS_CREATE_API = "http://api.map.baidu.com/geodata/v3/poi/create";

    private static final String LBS_QUERY_API = "http://api.map.baidu.com/geodata/v3/poi/list?";

    private static final String LBS_UPDATE = "http://api.map.baidu.com/geodata/v3/poi/update";

    private static final String LBS_DELETE = "http://api.map.baidu.com/geodata/v3/poi/delete";

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Qualifier(value = "ObjectMapper.class")
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress,SupportAddressDTO.class);
            addressDTOS.add(target);
        }
        return new ServiceMultiResult<>(addressDTOS.size(),addressDTOS);
    }



    @Override
    public Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        return null;
    }

    @Override
    public ServiceMultiResult findAllRegionsByCityName(String cityName) {
        return null;
    }

    @Override
    public List<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        return null;
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        return null;
    }

    @Override
    public ServiceResult<SubwayDTO> findSubway(Long subwayLineId) {
        return null;
    }

    @Override
    public ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId) {
        if (stationId == null){
            return ServiceResult.notFound();
        }
        SubwayStation station = subwayStationRepository.findOne(stationId);
        if (station == null){
            return ServiceResult.notFound();
        }
        return ServiceResult.of(modelMapper.map(station,SubwayStationDTO.class));
    }

    @Override
    public ServiceResult<SupportAddressDTO> findCity(String cityEnName) {
        if (cityEnName == null){
            return ServiceResult.notFound();
        }

        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName,SupportAddress.Level.CITY.getValue());
        if (supportAddress == null){
            return ServiceResult.notFound();
        }
        SupportAddressDTO addressDTO = modelMapper.map(supportAddress, SupportAddressDTO.class);

        return ServiceResult.of(addressDTO);
    }

    @Override
    public ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address) {
        return null;
    }

    @Override
    public ServiceResult lbsUpload(BaiduMapLocation location, String title, String address, long houseId, int price, int area) {

        return null;
    }

    @Override
    public ServiceResult removeLbs(Long houseId) {
        return null;
    }
}
