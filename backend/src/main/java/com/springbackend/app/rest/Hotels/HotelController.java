package com.springbackend.app.rest.Hotels;

import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URLEncoder;


@RequestMapping(path="/api/hotel")
@RestController
public class HotelController {

<<<<<<< HEAD
    @Autowired
    private HotelsRepo hotelsRepo;

=======
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
    @GetMapping(path="/latlong")
    public String getLatLong(@RequestParam String fullAddress) throws IOException {

        /* Encode the address */
        String query = URLEncoder.encode(fullAddress, "UTF-8");

        /* Get API key and create URL to query */
        final String positionStackKey = System.getenv("POSITION_STACK");

        /* Call to PositionStack address to coordinates API */
        OkHttpClient latLongClient = new OkHttpClient();
        Request latLongRequest = new Request.Builder()
                .url("http://api.positionstack.com/v1/forward?access_key=" + positionStackKey + "&query=" + fullAddress)
                .get()
                .build();
        Response latLongResponse = latLongClient.newCall(latLongRequest).execute();


        /* Convert response to string */
        String latLongString = latLongResponse.body().string();

        /* Convert response to JsonArray */
        JsonObject nearbySearchJsonObject = new Gson().fromJson(latLongString, JsonObject.class);
        JsonArray latLongArray = nearbySearchJsonObject.getAsJsonArray("data");

        /* String that will be returned */
        String returnString = new String();

        /* Iterate over latLongArray and extract lat/long values */
        for(JsonElement jsonIterator : latLongArray){
            JsonObject dataObject = jsonIterator.getAsJsonObject();
            String latitude = dataObject.get("latitude").getAsString();
            String longitude = dataObject.get("longitude").getAsString();
            returnString = latitude + "," + longitude;

        }

        return returnString;
    }

    @GetMapping(path="nearbyHotels")
<<<<<<< HEAD
    public JsonArray nearbyHotels(@RequestParam String location) throws IOException {

        /* Determine the coordinates of location */
        String destCords = getLatLong(location);
=======
    public JsonArray nearbyHotels(@RequestParam String fullAddress) throws IOException {

        /* Determine the coordinates of fullAddress */
        String destCords = getLatLong(fullAddress);
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc

        /* Get API key */
        String tripAdvisorAPI = System.getenv("TRIP_ADVISOR");

        /* API call configuration for TripAdvisor nearby_search endpoint */
        OkHttpClient nearbySearchClient = new OkHttpClient();
        Request nearbySearchRequest = new Request.Builder()
                .url("https://api.content.tripadvisor.com/api/v1/location/nearby_search?latLong=" + destCords + "&key="
                        + tripAdvisorAPI + "&category=hotels&radius=20&radiusUnit=mi&language=en")
                .get()
                .addHeader("accept", "application/json")
                .build();
        Response nearbySearchResponse = nearbySearchClient.newCall(nearbySearchRequest).execute();

        /* Converting nearbySearchResponse to string */
        String nearbySearchResponseString = nearbySearchResponse.body().string();

        /* Converting nearbySearchResponseString into a json array of json objects to be parsed */
        JsonObject nearbySearchJsonObject = new Gson().fromJson(nearbySearchResponseString, JsonObject.class);
        JsonArray nearbyLocationSearchArray = nearbySearchJsonObject.getAsJsonArray("data");

        /* JsonArray that will be returned containing information of hotels at a given location */
        JsonArray hotelArray = new JsonArray();

        for(JsonElement jsonIterator : nearbyLocationSearchArray){

<<<<<<< HEAD
            /* Meet Bob! He will help you build your hotel! */
            Hotels.HotelsBuilder bob = new Hotels.HotelsBuilder();

=======
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
            /* Create a new hotelJsonObject each iteration to add to hotelArray */
            JsonObject hotelJsonObject = new JsonObject();

            JsonObject dataObject = jsonIterator.getAsJsonObject();

            /* Extract the desired fields from the data object */
            String locationId = dataObject.get("location_id").getAsString();
            String name = dataObject.get("name").getAsString();
<<<<<<< HEAD
            String fullAddress = dataObject.get("address_obj").getAsJsonObject().get("address_string").getAsString();
=======
            String addressString = dataObject.get("address_obj").getAsJsonObject().get("address_string").getAsString();
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc

            /* Add properties to hotelJsonObject */
            hotelJsonObject.addProperty("location_id", locationId);
            hotelJsonObject.addProperty("name", name);
<<<<<<< HEAD
            hotelJsonObject.addProperty("address_string", fullAddress);

            /* Give Bob some information to pick up */
            bob.locationID(locationId);
            bob.hotelName(name);
            bob.fullAddress(fullAddress);
=======
            hotelJsonObject.addProperty("address_string", addressString);
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc

            /*
                Getting more information on each hotel returned by nearby_search API.
                Use the location_id to query the location_details API and extract
                desired values.
             */
            OkHttpClient locationDetailsClient = new OkHttpClient();
            Request locationSearchRequest = new Request.Builder()
                    .url("https://api.content.tripadvisor.com/api/v1/location/" + locationId
<<<<<<< HEAD
                            + "/details?key=" + tripAdvisorAPI + "&language=en&ddcurrency=USD")
=======
                            + "/details?key=" + tripAdvisorAPI + "&language=en&currency=USD")
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
                    .get()
                    .addHeader("accept", "application/nearbySearchResponseString")
                    .build();

            /* Raw API nearbySearchResponse */
            Response locationDetailsResponse = locationDetailsClient.newCall(locationSearchRequest).execute();

<<<<<<< HEAD
=======

>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
            /* Converting raw nearbySearchResponse to string */
            String locationDetailsResponseString = locationDetailsResponse.body().string();

            /* Converting responseString into Json for processing */
            Gson gson = new Gson();
            JsonObject locationSearchJsonObject = gson.fromJson(locationDetailsResponseString, JsonObject.class);

<<<<<<< HEAD
            /* Extract the description field and hand-off to Bob */
=======

            /* Extract the description field*/
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
            if(locationSearchJsonObject.has("description")){
                String description = locationSearchJsonObject.get("description").getAsString();
                description = description.replaceAll("\\n", "");
                hotelJsonObject.addProperty("description", description);
<<<<<<< HEAD

                bob.description(description);
            }


            /* Extract the rating field and hand-off to Bob */
            if(locationSearchJsonObject.has("rating")) {
                String rating = locationSearchJsonObject.get("rating").getAsString();
                hotelJsonObject.addProperty("rating", rating);

                bob.rating(rating);
            }


            /* Extract the link to view more photos and hand-off to Bob */
            if(locationSearchJsonObject.has("see_all_photos")) {
                String imagesUrl = locationSearchJsonObject.get("see_all_photos").getAsString();
                hotelJsonObject.addProperty("images_url", imagesUrl);

                bob.photosURL(imagesUrl);
            }


            /* Extract the price level and hand-off to Bob */
            if(locationSearchJsonObject.has("price_level")) {
                String priceLevel = locationSearchJsonObject.get("price_level").getAsString();
                hotelJsonObject.addProperty("price_level", priceLevel);

                bob.priceLevel(priceLevel);
            }


            /* Extract the link to the hotel's website and hand-off to Bob */
=======
            }
            /* Extract the rating field */
            if(locationSearchJsonObject.has("rating")) {
                String rating = locationSearchJsonObject.get("rating").getAsString();
                hotelJsonObject.addProperty("rating", rating);
            }

            /* Extract the link to view more photos */
            if(locationSearchJsonObject.has("see_all_photos")) {
                String imagesUrl = locationSearchJsonObject.get("see_all_photos").getAsString();
                hotelJsonObject.addProperty("images_url", imagesUrl);
            }

            /* Extract the price level */
            if(locationSearchJsonObject.has("price_level")) {
                String priceLevel = locationSearchJsonObject.get("price_level").getAsString();
                hotelJsonObject.addProperty("price_level", priceLevel);
            }

            /* Extract the link to the hotel's website */
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
            if(locationSearchJsonObject.has("website")) {
                String websiteURL = locationSearchJsonObject.get("website").getAsString();
                hotelJsonObject.addProperty("website_url", websiteURL);

<<<<<<< HEAD
                bob.websiteURL(websiteURL);
            }

            
            /* Use the information Bob has gathered to build our hotel */
            Hotels hotel = bob.build();

            /* Save the new hotel to the database */
            hotelsRepo.save(hotel);

            cleanDatabase();

=======
            }

>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
            /* Add the instance of hotelJsonObject to the returning json array */
            hotelArray.add(hotelJsonObject);
        }
        return hotelArray;
    }

<<<<<<< HEAD
    private void cleanDatabase(){

    }

=======
>>>>>>> f4066381d41253f71a63680c450ddcf3f103c4fc
}