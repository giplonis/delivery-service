import React from 'react';
import Grid from '@material-ui/core/Grid';
import BoxImage from '../images/box.jpg'
import ParcelSizeCard from './ParcelSizeCard';

function ParcelSize(props){
    return(
        <Grid container justify="center" spacing={10}>
            {props.boxSizes.map((size, index) => 
                <Grid item key={index}>
                    <ParcelSizeCard
                        image = {BoxImage}
                        name = {size.name}
                        selectedPackageSize = {props.selectedBoxSize}
                        onClick = {props.onChange}
                        dimensions = {{
                            width: size.width, 
                            height: size.height, 
                            length: size.length
                        }}
                    />
                </Grid>
            )}
        </Grid>
    )
}

export default ParcelSize