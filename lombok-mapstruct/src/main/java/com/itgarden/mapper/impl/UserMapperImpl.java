package com.itgarden.mapper.impl;

import com.itgarden.dto.AddressDto;
import com.itgarden.dto.RoleDto;
import com.itgarden.dto.UserDto;
import com.itgarden.entity.Address;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import com.itgarden.mapper.UserMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-17T23:33:55+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOtoUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setEmailId( userDto.getEmailId() );
        user.setFirstName( userDto.getFirstName() );
        user.setMiddleName( userDto.getMiddleName() );
        user.setLastName( userDto.getLastName() );
        user.setMobileNo( userDto.getMobileNo() );
        user.setAddressList( addressDtoListToAddressList( userDto.getAddressList() ) );
        user.setRoles( roleDtoListToRoleList( userDto.getRoles() ) );

        return user;
    }

    @Override
    public UserDto userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setEmailId( user.getEmailId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setMiddleName( user.getMiddleName() );
        userDto.setLastName( user.getLastName() );
        userDto.setMobileNo( user.getMobileNo() );
        userDto.setAddressList( addressListToAddressDtoList( user.getAddressList() ) );
        userDto.setRoles( roleListToRoleDtoList( user.getRoles() ) );

        return userDto;
    }

    protected Address addressDtoToAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDto.getId() );
        address.setAddress1( addressDto.getAddress1() );
        address.setAddress2( addressDto.getAddress2() );
        address.setCity( addressDto.getCity() );
        address.setState( addressDto.getState() );
        address.setCountry( addressDto.getCountry() );
        address.setLandmark( addressDto.getLandmark() );
        address.setMobile( addressDto.getMobile() );

        return address;
    }

    protected List<Address> addressDtoListToAddressList(List<AddressDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressDto addressDto : list ) {
            list1.add( addressDtoToAddress( addressDto ) );
        }

        return list1;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );
        role.setDescription( roleDto.getDescription() );

        return role;
    }

    protected List<Role> roleDtoListToRoleList(List<RoleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDto roleDto : list ) {
            list1.add( roleDtoToRole( roleDto ) );
        }

        return list1;
    }

    protected AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setId( address.getId() );
        addressDto.setAddress1( address.getAddress1() );
        addressDto.setAddress2( address.getAddress2() );
        addressDto.setCity( address.getCity() );
        addressDto.setState( address.getState() );
        addressDto.setCountry( address.getCountry() );
        addressDto.setLandmark( address.getLandmark() );
        addressDto.setMobile( address.getMobile() );

        return addressDto;
    }

    protected List<AddressDto> addressListToAddressDtoList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDto> list1 = new ArrayList<AddressDto>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressDto( address ) );
        }

        return list1;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );
        roleDto.setDescription( role.getDescription() );

        return roleDto;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }
}
