import React from 'react'
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';

function ParcelSizeCard(props){
    return(
        <Card style = {props.selectedPackageSize === props.name ? {boxShadow: "0 0 15px #2196f3"}:null}>
            <CardActionArea 
                onClick = {() => props.onClick(props.name)}
            >
                <Typography variant="h5" align="center" style = {{margin: "12px"}}>
                    {props.name}
                </Typography>
            
                <CardMedia
                    image={props.image}
                    style = {{width:"300px", height:"250px"}}
                />
                <Typography variant="body1" align="center" style = {{marginBottom: "10px"}}>
                    {`${props.dimensions.width} x ${props.dimensions.height} x ${props.dimensions.length}`}
                </Typography>
            </CardActionArea>
        </Card>
    )
}

export default ParcelSizeCard;