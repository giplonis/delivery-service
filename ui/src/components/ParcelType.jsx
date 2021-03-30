import React from 'react';
import Grid from '@material-ui/core/Grid';
import ParcelTypeCard from './ParcelTypeCard';
import DocumentImage from '../images/document.jpg'
import BoxImage from '../images/box.jpg'

function ParcelType(props){
    return(
        <Grid container justify="center" spacing={10}>
            <Grid item>
                <ParcelTypeCard
                    image = {DocumentImage}
                    name = "Document"
                    selectedPackageType = {props.selectedPackageType}
                    onClick = {props.onChange}
                />
            </Grid>
            <Grid item>
                <ParcelTypeCard
                    image = {BoxImage}
                    name = "Box"
                    selectedPackageType = {props.selectedPackageType}
                    onClick = {props.onChange}
                />
            </Grid>
        </Grid>
    )
}

export default ParcelType