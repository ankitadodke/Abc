package com.example.abc.ui.journey

import androidx.lifecycle.ViewModel
import com.example.abc.domain.model.ListItem

class MainViewModel : ViewModel() {
    // Sample data for images carousel
    val imageUrls = listOf(
        "https://picsum.photos/id/1/800/800",
        "https://picsum.photos/id/10/800/800",
        "https://picsum.photos/id/100/800/800",
        "https://picsum.photos/id/1000/800/800"
    )

    val listData = listOf(
        // Fruits list
        listOf(
            ListItem("Apple", "A round fruit with red or green skin and a whitish interior"),
            ListItem("Banana", "A long curved fruit with a yellow skin and soft sweet flesh"),
            ListItem("Cherry", "A small round stone fruit that is typically bright or dark red"),
            ListItem("Date", "A sweet dark brown oval fruit containing a hard narrow seed"),
            ListItem("Elderberry", "Small dark purple berries that grow in clusters"),
            ListItem("Fig", "A soft pear-shaped fruit with sweet dark flesh and many small seeds"),
            ListItem("Grape", "A small juicy fruit growing in clusters on vines"),
            ListItem("Honeydew", "A type of melon with a pale green flesh"),
            ListItem("Imbe", "African fruit with bright orange flesh and a tangy taste"),
            ListItem("Jackfruit", "Large tropical fruit with a sweet and fibrous flesh"),
            ListItem("Kiwi", "Brown fuzzy-skinned fruit with green flesh and black seeds"),
            ListItem("Lemon", "A yellow citrus fruit with acidic juice"),
            ListItem("Mango", "A juicy tropical fruit with a yellowish-red flesh"),
            ListItem("Nectarine", "A smooth-skinned variety of peach"),
            ListItem("Orange", "A round juicy citrus fruit with a tough bright reddish-yellow rind"),
            ListItem("Papaya", "A tropical fruit with soft orange flesh and black seeds"),
            ListItem("Quince", "A hard yellow pear-shaped fruit used in preserves"),
            ListItem("Raspberry", "An edible soft fruit related to the blackberry"),
            ListItem("Strawberry", "A sweet soft red fruit with seeds on its outer surface"),
            ListItem("Tangerine", "A small citrus fruit with a loose skin"),
            ListItem("Ugli fruit", "A Jamaican tangelo with a rough mottled greenish-yellow rind"),
            ListItem("Voavanga", "African fruit with a sweet-sour taste"),
            ListItem("Watermelon", "A large oblong or roundish fruit with watery flesh"),
            ListItem("Xigua", "Chinese watermelon variant with a dark green rind"),
            ListItem("Yangmei", "Chinese bayberry with sweet and tart flavor")
        ),
        // Nuts list
        listOf(
            ListItem("Almond", "An oval nut with a hard shell and a seed of delicate flavor"),
            ListItem("Brazil nut", "A large, three-sided nut with an edible white kernel"),
            ListItem("Cashew", "A curved edible nut grown at the end of the cashew apple"),
            ListItem("Chestnut", "A glossy brown nut that may be roasted and eaten"),
            ListItem("Coconut", "Large seed with a hard shell and an edible white inner flesh"),
            ListItem("Hazelnut", "A round hard-shelled nut with a kernel of rich sweet flavor"),
            ListItem("Macadamia", "A hard-shelled nut with a delicate flavor and high oil content"),
            ListItem("Peanut", "A legume pod with edible oily seeds"),
            ListItem("Pecan", "A smooth oval nut with a thin shell and edible kernel"),
            ListItem("Pistachio", "A small greenish nut with a distinctive flavor"),
            ListItem("Walnut", "The large wrinkled edible seed of a deciduous tree"),
            ListItem("Pine nut", "The edible seed of certain species of pine trees")
        ),
        // Vegetables list
        listOf(
            ListItem("Asparagus", "A spring vegetable with tender stalks and a delicate flavor"),
            ListItem("Broccoli", "A green plant with a dense flowery head"),
            ListItem("Carrot", "A long tapering orange-colored root eaten as a vegetable"),
            ListItem("Daikon", "A mild-flavored winter radish with a white root"),
            ListItem("Eggplant", "A glossy, purple-skinned egg-shaped vegetable"),
            ListItem("Fennel", "A white or pale green bulb with feathery fronds"),
            ListItem("Garlic", "A pungent bulb used as a flavoring agent"),
            ListItem("Horseradish", "A hot-tasting root used in cooking or in pickled form"),
            ListItem("Iceberg lettuce", "A variety of lettuce with crisp tightly packed leaves"),
            ListItem("Jalapeño", "A very hot chili pepper used in Mexican cooking"),
            ListItem("Kale", "A hardy cabbage with curled leaves that do not form a head"),
            ListItem("Leek", "A plant related to the onion with a cylindrical white bulb"),
            ListItem("Mushroom", "The fleshy spore-bearing fruiting body of a fungus"),
            ListItem("Nori", "Edible seaweed used especially in Japanese cuisine"),
            ListItem("Onion", "A round vegetable with a brown skin that has many layers"),
            ListItem("Potato", "A starchy plant tuber which is one of the most important food crops"),
            ListItem("Quinoa", "A grain crop grown primarily for its edible seeds"),
            ListItem("Radish", "A swollen pungent-tasting edible root"),
            ListItem("Spinach", "A green leafy vegetable rich in iron"),
            ListItem("Turnip", "A round root with white flesh which is eaten as a vegetable"),
            ListItem("Ube", "A purple yam commonly used in Filipino desserts"),
            ListItem("Valerianella", "A salad green also known as corn salad or lamb's lettuce"),
            ListItem("Watercress", "A leafy plant that grows in water with edible pungent leaves"),
            ListItem("Yam", "A starchy tuber with a brown tough skin")
        )
    )

    fun calculateStatistics(list: List<ListItem>): Map<String, Any> {
        val charFrequency = mutableMapOf<Char, Int>()
        list.forEach { item ->
            item.title.lowercase().filter { it.isLetter() }.forEach { char ->
                charFrequency[char] = charFrequency.getOrDefault(char, 0) + 1
            }
        }

        val topChars = charFrequency.entries.sortedByDescending { it.value }.take(3)
        return mapOf(
            "count" to list.size,
            "topChars" to topChars.map { it.key to it.value }
        )
    }
}