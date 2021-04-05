import {Grid, List, ListItem} from "@material-ui/core"

export default function OrderHistory(){
    
    const orders = [
        {
            sender: { name: "qwer", surname: "asdf", number: "+6546464646", city: "Vilnius", address: "asdfafds g. 10" },
            recipient: { name: "dsfgsrg", surname: "dfghdfgdh", number: "+98494984484", city: "Vilnius", address: "sdfdsfsf g. 10" },
            email: "sdfgsgfd@dfgdfg.dsf",
            pickUpDate: "2021-05-05T20:10",
            status: "New",
            packageId: 1
        },
        {
            sender: { name: "fghnfhgn", surname: "fnhgn", number: "+6546464646", city: "Vilnius", address: "asdfafds g. 10" },
            recipient: { name: "bghdn", surname: "dfbdfgb", number: "+98494984484", city: "Vilnius", address: "sdfdsfsf g. 10" },
            email: "sfaf@gmail.com",
            pickUpDate: "2021-04-05T13:10",
            status: "Picked Up",
            packageId: 2
        },
        {
            sender: { name: "nhtrh", surname: "fbggbd", number: "+6546464646", city: "Vilnius", address: "vfvgv g. 10" },
            recipient: { name: "dsfgsrg", surname: "bbvgb", number: "+98494984484", city: "Vilnius", address: "sdfdsfsf g. 10" },
            email: "asdfsd@gmail.com",
            pickUpDate: "2021-04-07T09:05",
            status: "Delivered",
            packageId: 5
        }
    ]
    const packageSizes = [
        { id: 1, type:"Box", name: "Small Box", width: 35, height: 16, length: 45, weight: 2 },
        { id: 2, type:"Box", name: "Medium Box", width: 46, height: 46, length: 64, weight: 20 },
        { id: 3, type:"Box", name: "Large Box", width: 150, height: 150, length: 150, weight: 30 },
        { id: 4, type:"Letter", name: "Small Letter", weight: 100, length: 24, width: 16 },
        { id: 5, type:"Letter", name: "Large Letter", weight: 750, length: 35, width: 25 },
    ];
    

    return(
        <List>
            {
                orders.map((order, index) => 
                    <ListItem button key={index}>
                        <Grid container>
                            <Grid item xs={4}>
                                {`Status: ${order.status}`}
                            </Grid>
                            <Grid item xs={4}>
                                {`Recipient: ${order.recipient.name}`}
                            </Grid>
                            <Grid item xs={4}>
                                {`Package: ${packageSizes.find(x =>  x.id === order.packageId).name}`}
                            </Grid>
                        </Grid>
                    </ListItem>
                )
            }
        </List>
    )


}