package com.example.data.preloaded

import com.example.data.model.SafetyGuide
import com.example.data.parser.SafetyGuideParser

object PreloadedGuides {

    val guidesMap: Map<String, String> = mapOf(
        "SLEEP" to """
SLEEP

✅ What Parents Should Know
• Age 1–2 needs 11–14 hours per day
• Age 3–5 needs 10–13 hours; Age 6–7 needs 9–12 hours

⚠️ Safety Risks
• Chronic overtiredness impairs mood, immunity, and focus
• Unsafe bedding or climb hazards cause night falls

🛡️ Safe Practice
• Keep bedrooms cool, dark, quiet, and screen-free
• Maintain a predictable 20-minute calm bedtime routine

👶 Age 1–3
• Transition safely to toddler bed once child climbs crib rails

🧒 Age 4–7
• Set regular wake times even on weekends to protect circadian rhythm

🚑 Get Help Now If
• Loud persistent snoring, gasping, or pauses in breathing
• Extreme daytime sleepiness or sudden sleep regressions

🎯 Today's Parent Action
• Turn off all digital screens 60 minutes before bedtime
""".trimIndent(),

        "WATER" to """
WATER

✅ What Parents Should Know
• Age 1–3 needs approximately 1.0–1.3 liters daily
• Age 4–7 needs approximately 1.3–1.7 liters daily

⚠️ Safety Risks
• Young kids dehydrate rapidly during heat or illness
• Sugary drinks displace essential hydration and nutrients

🛡️ Safe Practice
• Offer plain water regularly between meals and after active play
• Keep water accessible in a spill-proof cup

👶 Age 1–3
• Offer water with every meal and after playground activity

🧒 Age 4–7
• Send a labeled reusable water bottle to preschool or school

🚑 Get Help Now If
• No wet diapers for 6+ hours or no urination for 8+ hours
• Dry mouth, sunken eyes, or crying without tears

🎯 Today's Parent Action
• Fill a child-friendly water bottle and place it within your child's reach
""".trimIndent(),

        "FEVER" to """
FEVER

✅ What Parents Should Know
• Fever is the body's normal, healthy defense fighting infection
• Treat child comfort and hydration, not just the number

⚠️ Safety Risks
• Dehydration from refusing fluids during fever spikes
• Accidental medication overdosing or double-dosing

🛡️ Safe Practice
• Keep room comfortably cool and dress child in light layers
• Use syringe provided with medicine; check exact weight-based dose

👶 Age 1–3
• Encourage frequent small sips of water or oral rehydration solution

🧒 Age 4–7
• Never give aspirin to children or teens under any circumstance

🚑 Get Help Now If
• Fever above 104°F (40°C) or lasting more than 3 days
• Lethargy, stiff neck, rash, or difficulty breathing

🎯 Today's Parent Action
• Write down fever times and medicine doses on a tracker pad
""".trimIndent(),

        "CHOKING" to """
CHOKING

✅ What Parents Should Know
• Toddler airways are roughly the diameter of a drinking straw
• Choking is silent; children cannot cough or call for help

⚠️ Safety Risks
• Round, firm foods (whole grapes, hotdogs, nuts, hard candy)
• Small toy parts, coins, and balloon pieces

🛡️ Safe Practice
• Always supervise eating; insist children sit down while chewing
• Quarter round foods lengthwise into small strips

👶 Age 1–3
• Cut grapes, cherry tomatoes, and sausages lengthwise into quarters

🧒 Age 4–7
• Teach eating rule: no talking or running with food in mouth

🚑 Get Help Now If
• Child cannot breathe, speak, cry, or turns blue (call 911 immediately)
• Perform immediate age-appropriate back blows and abdominal thrusts

🎯 Today's Parent Action
• Inspect today's snack and cut all round items lengthwise
""".trimIndent(),

        "BATH" to """
BATH

✅ What Parents Should Know
• Young children can drown silently in under 2 inches of water
• Drowning can happen in less than 30 seconds without splashing

⚠️ Safety Risks
• Leaving child unattended even for a momentary phone glance
• Scalding from sudden hot water tap changes

🛡️ Safe Practice
• Keep touch supervision: remain within arm's reach at all times
• Set home water heater thermostat to maximum 120°F (49°C)

👶 Age 1–3
• Gather towels and soap before placing child in tub; never step away

🧒 Age 4–7
• Teach never to touch the hot water tap handle independently

🚑 Get Help Now If
• Child submerged or inhaled bath water with coughing or sputtering
• Lethargy or breathing struggle hours after bath (secondary drowning)

🎯 Today's Parent Action
• Test bath water with your wrist or bath thermometer before child enters
""".trimIndent(),

        "CAR" to """
CAR

✅ What Parents Should Know
• Car crashes are a leading cause of preventable childhood injury
• Proper restraint reduces severe injury risk by over 70%

⚠️ Safety Risks
• Prematurely graduating child to a forward seat or booster
• Loose harness straps or heavy winter coats under chest clip

🛡️ Safe Practice
• Keep children in back seat until at least age 13
• Fasten chest clip snugly at armpit level

👶 Age 1–3
• Keep rear-facing as long as possible up to seat height/weight limits

🧒 Age 4–7
• Use 5-point harness forward-facing, then high-back belt-positioning booster

🚑 Get Help Now If
• Any vehicle collision occurs with child inside (have pediatrician check)
• Child was left inside a parked car in hot weather (call 911)

🎯 Today's Parent Action
• Do the pinch test: if you can pinch harness webbing at shoulder, tighten it
""".trimIndent(),

        "MEDICINE" to """
MEDICINE

✅ What Parents Should Know
• Children metabolize drugs differently; always dose by weight, not age
• Child-resistant caps are not child-proof; kids can open them

⚠️ Safety Risks
• Storing medicines on low counters, nightstands, or in purses
• Giving adult formulas or alternating fever reducers without doctor approval

🛡️ Safe Practice
• Store all medications up, away, and locked out of sight and reach
• Always use the dosing syringe or cup packaged with that medicine

👶 Age 1–3
• Never call medicine 'candy' or sweet treat to encourage taking it

🧒 Age 4–7
• Teach that medicine must only be given by a trusted parent or nurse

🚑 Get Help Now If
• Suspected accidental ingestion or wrong dose (Call Poison Help 1-800-222-1222)
• Vomiting, unresponsiveness, or breathing changes after taking medicine

🎯 Today's Parent Action
• Save Poison Control number (1-800-222-1222) into your phone contacts
""".trimIndent(),

        "SCREEN" to """
SCREEN

✅ What Parents Should Know
• Screen time displaces interactive play, movement, language, and sleep
• High-paced media overstimulates immature nervous systems

⚠️ Safety Risks
• Meltdowns from sudden transitions without preparation
• Delayed speech and sleep disruptions from nighttime devices

🛡️ Safe Practice
• Co-view quality content and discuss what you see together
• Keep bedrooms, meal tables, and morning routines screen-free zones

👶 Age 1–3
• Limit to high-quality programming with an adult present; max 1 hour

🧒 Age 4–7
• Establish consistent daily limits and turn off screens 1 hour before sleep

🚑 Get Help Now If
• Extreme aggression, eye-rubbing pain, or loss of interest in active play
• Child cannot fall asleep without an active glowing screen

🎯 Today's Parent Action
• Create a charging basket in the kitchen where devices sleep overnight
""".trimIndent(),

        "ALLERGY" to """
ALLERGY

✅ What Parents Should Know
• Food allergies can develop at any age, even after prior safe exposures
• Top allergens include peanut, tree nuts, milk, egg, wheat, soy, fish

⚠️ Safety Risks
• Cross-contamination in home kitchens or daycare settings
• Delaying epinephrine during an acute anaphylaxis reaction

🛡️ Safe Practice
• Read all ingredient labels carefully every single time you buy
• Keep emergency action plan and doctor-prescribed medicine with child

👶 Age 1–3
• Introduce one new common allergen at a time during morning hours

🧒 Age 4–7
• Teach child never to share food, snacks, or utensils at school

🚑 Get Help Now If
• Swelling of lips/tongue, wheezing, hives with vomiting, or throat tightness
• Administer epinephrine auto-injector immediately and call 911

🎯 Today's Parent Action
• Check expiration dates on your child's epinephrine auto-injectors or antihistamines
""".trimIndent(),

        "TANTRUM" to """
TANTRUM

✅ What Parents Should Know
• Tantrums are normal brain development, not intentional defiance
• Big emotions overwhelm an immature prefrontal cortex

⚠️ Safety Risks
• Flailing into sharp furniture corners, walls, or hard floors
• Running into street or dangerous areas during an emotional surge

🛡️ Safe Practice
• Stay calm and breathe; your nervous system regulates their nervous system
• Protect physical safety first, then offer quiet physical presence

👶 Age 1–3
• Move child gently away from hard edges; keep words brief and soothing

🧒 Age 4–7
• Acknowledge feelings once calm: 'You were really angry, and that is okay'

🚑 Get Help Now If
• Tantrums involve severe self-harm (head-banging causing injury)
• Persistent aggression or meltdowns lasting over 25 minutes regularly

🎯 Today's Parent Action
• Take three deep breaths before responding the next time your child cries
""".trimIndent(),

        "POOL" to """
POOL

✅ What Parents Should Know
• Water safety requires layers of protection: fences, alarms, and eyes
• Floaties and puddle jumpers are swimming aids, not life preservers

⚠️ Safety Risks
• Silent drowning while multiple adults assume someone else is watching
• Suction entrapment in pool drains and slippery deck head strikes

🛡️ Safe Practice
• Assign one designated sober 'Water Watcher' wearing a visible badge
• Install 4-sided pool fencing at least 4 feet high with self-latching gate

👶 Age 1–3
• Maintain touch supervision: adult stays in water within arm's reach

🧒 Age 4–7
• Enroll in survival swim lessons; enforce strict no-running deck rule

🚑 Get Help Now If
• Child submerged or coughing after swimming (call 911 immediately)
• Child seems abnormally sleepy or breathes rapidly hours after pool play

🎯 Today's Parent Action
• Establish a dedicated Water Watcher tag for your family swim outings
""".trimIndent(),

        "BURNS" to """
BURNS

✅ What Parents Should Know
• Young children's skin is much thinner and burns at lower temperatures
• Hot liquids (tea, coffee, soup) cause the vast majority of scald burns

⚠️ Safety Risks
• Dangling appliance cords (slow cookers, kettles, irons) pulled down
• Hot pans on front stove burners and hot oven door glass

🛡️ Safe Practice
• Cook on back burners only and turn pot handles inward toward the wall
• Never hold a hot drink and a child at the same time

👶 Age 1–3
• Keep cups with hot liquids far out of reach from table edges

🧒 Age 4–7
• Teach children the '3-foot safety zone' around stove and grill

🚑 Get Help Now If
• Burn is larger than child's palm, on face/hands/joints, or blisters open
• Cool immediately with room temperature water for 10 minutes; call doctor

🎯 Today's Parent Action
• Push all hot beverage mugs and kettle cords to the back of kitchen counters
""".trimIndent(),

        "BATTERY" to """
BATTERY

✅ What Parents Should Know
• Button and coin batteries can burn through an esophagus in 2 hours
• Lithium batteries generate an electrical current when lodged in tissue

⚠️ Safety Risks
• Loose remote controls, singing greeting cards, and lighted toys
• Button batteries trapped in nose, ears, or swallowed unnoticed

🛡️ Safe Practice
• Tape battery compartments shut or keep devices out of child reach
• Store all replacement batteries locked high and out of sight

👶 Age 1–3
• Never leave car key fobs or remotes within toddler grasp

🧒 Age 4–7
• Teach child never to put small objects or electronics in mouth

🚑 Get Help Now If
• Suspected battery ingestion (give honey if over age 1 and go to ER now)
• Sudden drooling, chest pain, coughing, or refusing to swallow

🎯 Today's Parent Action
• Inspect remotes, key fobs, and digital thermometers in your living room
""".trimIndent(),

        "STAIRS" to """
STAIRS

✅ What Parents Should Know
• Stair falls are among the most common causes of toddler head trauma
• Baby walkers on wheels are extremely dangerous near stairs

⚠️ Safety Risks
• Unlatched safety gates or gates installed with pressure instead of hardware
• Leaving toys, laundry baskets, or tripping hazards on stair treads

🛡️ Safe Practice
• Install hardware-mounted safety gates at both top and bottom of stairs
• Keep stairs completely clear of clutter, rugs, and loose items

👶 Age 1–3
• Hardware-mount the top gate to wall studs; never use pressure-fit at top

🧒 Age 4–7
• Teach holding the handrail every step and never jumping multiple steps

🚑 Get Help Now If
• Loss of consciousness, vomiting more than once, or pupil asymmetry
• Persistent crying, extreme lethargy, or weakness in arms or legs

🎯 Today's Parent Action
• Check that the safety gate at the top of your staircase is firmly locked
""".trimIndent(),

        "SUN" to """
SUN

✅ What Parents Should Know
• Childhood sunburns significantly increase lifetime skin cancer risks
• Sun rays are strongest between 10:00 AM and 4:00 PM

⚠️ Safety Risks
• Heat exhaustion, painful blistered skin, and dehydration during sunny play
• Reflected UV rays from water, sand, and concrete

🛡️ Safe Practice
• Apply broad-spectrum SPF 30+ mineral sunscreen 15 minutes before going out
• Dress children in lightweight UV-protective clothing and wide-brim hats

👶 Age 1–3
• Seek shade under trees or strollers; reapply sunscreen every 2 hours

🧒 Age 4–7
• Teach wearing UV-filtering sunglasses to protect developing eyes

🚑 Get Help Now If
• Severe blistering, fever, chills, dizziness, or vomiting after sun exposure
• Child stops sweating, has hot dry skin, or seems confused (heat stroke)

🎯 Today's Parent Action
• Place a broad-spectrum mineral sunscreen bottle near your front door
""".trimIndent(),

        "MILK" to """
MILK

✅ What Parents Should Know
• Whole cow's milk is introduced after 12 months, not before
• Maximum recommended cow's milk intake is 16–24 ounces (2–3 cups) daily

⚠️ Safety Risks
• Drinking too much milk displaces iron-rich foods, leading to anemia
• Unpasteurized (raw) milk carries severe bacterial infection risks (E. coli)

🛡️ Safe Practice
• Offer milk in cups, never bottles at bedtime, to prevent tooth decay
• Pair milk with balanced meals rich in iron, zinc, and fiber

👶 Age 1–3
• Whole cow's milk from age 1–2 supports healthy brain development

🧒 Age 4–7
• Transition to low-fat (1% or skim) milk around age 2 per pediatrician guidance

🚑 Get Help Now If
• Blood in stool, persistent vomiting, or hives appearing after dairy intake
• Facial swelling, wheezing, or difficulty breathing (anaphylaxis)

🎯 Today's Parent Action
• Measure your child's daily milk cups to verify it stays under 20 ounces
""".trimIndent(),

        "SOCKET" to """
SOCKET

✅ What Parents Should Know
• Electrical shocks can cause internal tissue burns and cardiac rhythm disruption
• Tamper-resistant outlets are safer than plug-in plastic caps kids remove

⚠️ Safety Risks
• Inserting metal forks, hairpins, keys, or keys into open socket slots
• Frayed appliance cords and overloading power strips on play room floors

🛡️ Safe Practice
• Install tamper-resistant outlet covers or sliding spring-loaded plate covers
• Route all electrical cords behind heavy furniture where kids cannot pull

👶 Age 1–3
• Use box covers over plugged-in cords so toddlers cannot yank plugs out

🧒 Age 4–7
• Teach that water and electricity are dangerous; never touch cords with wet hands

🚑 Get Help Now If
• Child receives electrical shock, falls unconscious, or has skin burn marks
• Never touch child if still in contact with electrical source; shut off breaker

🎯 Today's Parent Action
• Walk through your living room at toddler eye level and cover exposed sockets
""".trimIndent(),

        "DOG" to """
DOG

✅ What Parents Should Know
• Most pediatric dog bites occur with familiar family or neighborhood pets
• Dogs communicate stress subtly: yawning, licking lips, and turning head away

⚠️ Safety Risks
• Disturbing a sleeping dog, eating dog, or cornering a nervous pet
• Hugging dogs tightly around neck, kissing faces, or sudden tail pulling

🛡️ Safe Practice
• Always provide active adult supervision during child-dog interactions
• Teach children to ask pet owners for permission before petting any animal

👶 Age 1–3
• Never leave a toddler and a dog alone in a room together, even for 5 seconds

🧒 Age 4–7
• Teach the 'tree' pose: stand still with arms folded if an unfamiliar dog approaches

🚑 Get Help Now If
• Any dog bite breaks the skin (clean immediately, seek doctor visit for infection)
• Puncture wounds near face, neck, or hands, or bleeding that will not stop

🎯 Today's Parent Action
• Designate a safe pet quiet zone where your dog can rest undisturbed by kids
""".trimIndent(),

        "SCHOOL" to """
SCHOOL

✅ What Parents Should Know
• Starting preschool or kindergarten requires both emotional and physical safety
• Clear routines reduce separation anxiety and promote confident independence

⚠️ Safety Risks
• Traffic hazards at school crosswalks and parking drop-off zones
• Unreported playground injuries or early signs of peer bullying

🛡️ Safe Practice
• Practice the exact walking route and safe crosswalk procedures together
• Teach child their full legal name, parent full name, and phone number

👶 Age 1–3
• Establish a warm, predictable 30-second goodbye hug routine at daycare

🧒 Age 4–7
• Ask open questions after school: 'Who did you play with at recess today?'

🚑 Get Help Now If
• Sudden regression (bedwetting, intense panic attacks before school)
• Unexplained bruises or drastic changes in eating and sleeping habits

🎯 Today's Parent Action
• Role-play asking a teacher for help with bathroom or playground issues
""".trimIndent(),

        "POISON" to """
POISON

✅ What Parents Should Know
• Over 90% of childhood poison exposures happen in the home kitchen or bathroom
• Cleaning pods, cosmetics, essential oils, and plants are common poisons

⚠️ Safety Risks
• Colorful laundry pods look identical to gummy candy to young toddlers
• Storing toxic chemicals in repurposed beverage or soda bottles

🛡️ Safe Practice
• Lock household chemicals high in cabinets fitted with magnetic child locks
• Keep products in their original manufacturer containers with warning labels

👶 Age 1–3
• Switch to liquid or powder detergent rather than concentrated dissolvable pods

🧒 Age 4–7
• Teach child that unknown berries, mushrooms, and liquids are strictly off-limits

🚑 Get Help Now If
• Child swallowed household product or medicine (Call 1-800-222-1222 immediately)
• Vomiting, chemical burns around mouth, drowsiness, or difficulty breathing

🎯 Today's Parent Action
• Save the national Poison Help line (1-800-222-1222) as a speed-dial contact
""".trimIndent(),

        "HELMET" to """
HELMET

✅ What Parents Should Know
• A properly fitted helmet reduces serious traumatic brain injury risk by 85%
• Helmets must sit level across the forehead, not tilted back like a cap

⚠️ Safety Risks
• Wearing loose, damaged, or expired hand-me-down bicycle helmets
• Removing helmet during riding or wearing over thick winter beanies

🛡️ Safe Practice
• Follow the 2-V-1 rule: 2 fingers above eyebrows, V straps around ears, 1 finger under chin
• Replace any helmet that has sustained a hard crash or impact

👶 Age 1–3
• Put helmet on every single ride on tricycles, balance bikes, or scooter boards

🧒 Age 4–7
• Model helmet safety by wearing your own helmet whenever cycling together

🚑 Get Help Now If
• Child hits head, loses consciousness, vomits, or appears disoriented
• Unequal pupil sizes, persistent headache, or balance issues after a fall

🎯 Today's Parent Action
• Do the 2-V-1 check on your child's helmet before their next bike ride
""".trimIndent(),

        "WINDOW" to """
WINDOW

✅ What Parents Should Know
• Window screens are designed to keep insects out, NOT to hold child weight
• Falls from second-story windows can be fatal or cause catastrophic injury

⚠️ Safety Risks
• Open windows exceeding 4 inches in rooms where young children play
• Placing cribs, beds, chairs, or toy boxes directly beneath window sills

🛡️ Safe Practice
• Install window stops or guards that prevent opening more than 4 inches
• Move all climbable furniture at least 3 feet away from all windows

👶 Age 1–3
• Open double-hung windows from the top sash only, never the bottom

🧒 Age 4–7
• Teach children never to lean against window glass or push against screens

🚑 Get Help Now If
• Child falls from a window (do not move child; dial 911 immediately)
• Obvious limb deformity, head trauma, unconsciousness, or bleeding

🎯 Today's Parent Action
• Walk room-by-room and move chairs and toy chests away from all windows
""".trimIndent(),

        "TEETH" to """
TEETH

✅ What Parents Should Know
• Baby teeth hold space for permanent adult teeth and affect speech clarity
• First dental visit should occur by child's first birthday or first tooth

⚠️ Safety Risks
• Sending child to bed with a bottle or sippy cup containing milk or juice
• Excessive fluoride swallowing leading to dental fluorosis spots

🛡️ Safe Practice
• Brush twice daily for 2 minutes with soft-bristled brush and fluoride paste
• Use a rice-sized smear for ages 1–3, and a pea-sized amount for ages 4–7

👶 Age 1–3
• Parents should do 100% of the brushing; use tiny rice grain amount of paste

🧒 Age 4–7
• Child can practice brushing first, but parent must finish and inspect back molars

🚑 Get Help Now If
• Swollen red gums with fever, severe facial swelling, or dental abscess
• A primary or permanent tooth is knocked out during a fall or blow

🎯 Today's Parent Action
• Time tonight's bedtime tooth brushing session with a fun 2-minute song
""".trimIndent()
    )

    fun getGuide(word: String): SafetyGuide? {
        val key = word.trim().uppercase()
        val text = guidesMap[key] ?: return null
        return SafetyGuideParser.parse(word = key, rawText = text)
    }

    val suggestedWords = listOf(
        "sleep", "water", "fever", "choking", "medicine", "bath",
        "car", "screen", "allergy", "tantrum", "pool", "burns",
        "battery", "stairs", "sun", "milk", "socket", "dog",
        "school", "poison", "helmet", "window", "teeth"
    )

    val categorizedWords = mapOf(
        "Nutrition" to listOf("water", "milk", "choking", "allergy"),
        "Sleep" to listOf("sleep"),
        "Hygiene" to listOf("bath", "teeth"),
        "Home Safety" to listOf("burns", "battery", "stairs", "socket", "poison", "window"),
        "Medicine" to listOf("medicine", "fever"),
        "Outdoor" to listOf("car", "pool", "sun", "helmet", "dog"),
        "Digital" to listOf("screen"),
        "Emotional" to listOf("tantrum", "school")
    )
}
