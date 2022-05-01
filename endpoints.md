## Search
### Request 
`/api/entries`
 - Departure location `starting_place`
 - Destination location `destination`
 - How many people `number_of_people`
 - Start `start_date` - js date object
 - End `end_date`

### Response
 - City name `city`
 - Country `country`
 - Start date `startDate`
 - End date `endDate`
 - Price `price`
 - Image from the city `cityImage` - url for image
 - Sights to see `sights` json array

 ```json
 {
     "city": "Delft",
     ...
 }
 ```
