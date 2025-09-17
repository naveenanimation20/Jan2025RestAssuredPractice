package AuthAPIs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDestinationsResponse {
    private List<FlightDestination> data;
    private Dictionaries dictionaries;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FlightDestination {
        private String type;
        private String origin;
        private String destination;
        private String departureDate;
        private String returnDate;
        private Price price;
        private Links links;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Price {
            private String total;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Links {
            private String flightDates;
            private String flightOffers;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Dictionaries {
        private Map<String, String> currencies;
        private Map<String, Location> locations;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Location {
            private String subType;
            private String detailedName;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Meta {
        private String currency;
        private MetaLinks links;
        private Defaults defaults;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class MetaLinks {
            private String self;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Defaults {
            private String departureDate;
            private boolean oneWay;
            private String duration;
            private boolean nonStop;
            private String viewBy;
        }
    }
}